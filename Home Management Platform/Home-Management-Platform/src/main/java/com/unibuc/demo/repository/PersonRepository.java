package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Person;
import com.unibuc.demo.domain.User;
import com.unibuc.demo.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {
    @Autowired
    private UserRepository userRepository;

    private JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Person> getById(Long id) {
        String sql = "SELECT * FROM person p WHERE p.id = ?";
        RowMapper<Person> mapper = (resultSet, rowNum) -> {
            return new Person(resultSet.getLong("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age"),
                    resultSet.getLong("roleId"));
        };
        Person person = jdbcTemplate.queryForObject(sql, mapper, id);
        if (person != null)
            return Optional.of(person);
        return Optional.empty();
    }

    public List<Person> getAll() {
        String sql = "select * from person";
        RowMapper<Person> rowMapper = (resultSet, rowNo) -> Person.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("firstName"))
                .lastName(resultSet.getString("lastName"))
                .age(resultSet.getInt("age"))
                .roleId(resultSet.getLong("roleId"))
                .build();

        return jdbcTemplate.query(sql, rowMapper);
    }

    public void updatePersonDetailsByAdmin(long id, int age, String firstName, String lastName) {
        String sql = "update person p set p.age = ?,p.firstName = ?, p.lastName = ? where p.id = ?";
        int numberOfUpdatedPersonYear = jdbcTemplate.update(sql, age,firstName ,lastName , id);
        if (numberOfUpdatedPersonYear == 0) {
            throw new RuntimeException();
        }
    }

    public void deletePersonById(Long id){
        String sql="delete from person  where  id = ?";
        jdbcTemplate.update(sql,id);
    }

    public Person createPerson(Person person) {
        String sql = "insert into person values (?,?,?,?,?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setObject(5, person.getRoleId());

            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        generatedKeyHolder.getKey();
        person.setId(generatedKeyHolder.getKey().longValue());
        return person;
    }

    public void updatePersonOwnDetails(Person person, Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Person> person1 = getById(user.get().getPersonId());
        System.out.println(person.getId());
        System.out.println(person1.get().getId());
        if(person.getId() == person1.get().getId())
        {
            String sql = "update person p set p.age = ?,p.firstName = ?, p.lastName = ? where p.id = ?";

            int numberOfUpdatedPersonYear = jdbcTemplate.update(sql, person.getAge(),person.getFirstName() ,person.getLastName() , person1.get().getId());
            if (numberOfUpdatedPersonYear == 0) {
                throw new RuntimeException();
            }
        }
        else{
            throw new PersonNotFoundException(person1.get().getId(), person1.get().getFirstName());
        }

    }
}
