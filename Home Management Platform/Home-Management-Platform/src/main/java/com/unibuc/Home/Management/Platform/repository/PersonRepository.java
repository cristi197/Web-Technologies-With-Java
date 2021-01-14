package com.unibuc.Home.Management.Platform.repository;

import com.unibuc.Home.Management.Platform.domain.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {

    private JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Person> getById(Long id) {
        String sql = "SELECT * FROM persons p WHERE p.id = ?";
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
        String sql = "select * from persons";
        RowMapper<Person> rowMapper = (resultSet, rowNo) -> Person.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("firstName"))
                .lastName(resultSet.getString("lastName"))
                .age(resultSet.getInt("age"))
                .roleId(resultSet.getLong("roleId"))
                .build();

        return jdbcTemplate.query(sql, rowMapper);
    }

    public void updatePersonDetails(long id, int age, String firstName, String lastName) {
        String sql = "update persons p set p.age = ?,p.firstName = ?, p.lastName = ? where p.id = ?";
        int numberOfUpdatedPersonYear = jdbcTemplate.update(sql, age,firstName ,lastName , id);
        if (numberOfUpdatedPersonYear == 0) {
            throw new RuntimeException();
        }
    }

    public void deletePersonById(Long id){
        String sql="delete from persons  where  id = ?";
        jdbcTemplate.update(sql,id);
    }

    public Person createPerson(Person person) {
        String sql = "insert into persons values (?,?,?,?,?)";
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
/*        String sql="insert into bankaccounts values (?,?,?,?,?)";
        jdbcTemplate.update(sql,null, bankAccount.getAccountNumber(),
                bankAccount.getBalance(),bankAccount.getType().toString(),bankAccount.getCardNumber());*/
    }
    /*    public PersonRepository() {
        loadData();
    }*/

/*    public Person save(Person person) {
        persons.add(person);
        return person;
    }

    public Person update(Person person) {
        Optional<Person> optionalPerson = persons.stream()
                .filter(person1 -> person1.getFirstName().equals(person.getFirstName()))
                .findAny();
        if (optionalPerson.isPresent()) {
            persons.remove(optionalPerson.get());
            persons.add(person);
            return person;
        }
        return null;
    }

    public List<Person> getAll() {
        return persons;
    }

    public void delete(Person person) {
        persons.remove(person);
    }

    private void loadData() {
        Person person1 = Person.builder()
                .firstName("Cristian")
                .lastName("Teodorescu")
                .age(23)
                .build();
        persons.add(person1);
        Person person2 = Person.builder()
                .firstName("Andrei")
                .lastName("Ionescu")
                .age(14)
                .build();
        persons.add(person2);
    }*/


}
