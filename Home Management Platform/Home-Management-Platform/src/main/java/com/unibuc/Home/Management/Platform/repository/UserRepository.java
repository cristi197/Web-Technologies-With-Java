package com.unibuc.Home.Management.Platform.repository;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.domain.Role;
import com.unibuc.Home.Management.Platform.domain.Task;
import com.unibuc.Home.Management.Platform.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository  {
    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<User> findByUsername(String username){
        String sql = "SELECT * FROM user u WHERE u.userName = ?";
        RowMapper<User> mapper = (resultSet, rowNum) -> {
            return new User(resultSet.getLong("id"),
                    resultSet.getString("userName"),
                    resultSet.getString("password"),
                    resultSet.getLong("personId"),
                    resultSet.getLong("roleId"));
        };
        User user = jdbcTemplate.queryForObject(sql, mapper, username);
        System.out.println(user.getUsername());
        if (user != null)
            return Optional.of(user);
        return Optional.empty();
    }

    public User save(User user, Person person) {
        String sql = "insert into user values (?,?,?,?,?)";
        System.out.println(user.getUsername());
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, person.getId());
            preparedStatement.setLong(5, user.getRoleId());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        generatedKeyHolder.getKey();
        user.setId(generatedKeyHolder.getKey().longValue());
        return user;
    }

    public List<User> getAll() {
        String sql = "select * from user";
        RowMapper<User> rowMapper = (resultSet, rowNo) -> User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("userName"))
                .password(resultSet.getString("password"))
                .personId(resultSet.getLong("personId"))
                .roleId(resultSet.getLong("roleId"))
                .build();

        return jdbcTemplate.query(sql, rowMapper);
    }
}
