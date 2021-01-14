package com.unibuc.Home.Management.Platform.repository;

import com.unibuc.Home.Management.Platform.domain.Role;
import com.unibuc.Home.Management.Platform.domain.Status;
import com.unibuc.Home.Management.Platform.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository {
    private JdbcTemplate jdbcTemplate;

    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<Role> findByName(String name){
        String sql = "SELECT * FROM roles r WHERE r.name = ?";
        RowMapper<Role> mapper = (resultSet, rowNum) -> {
            return new Role(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"));
        };
        Role role = jdbcTemplate.queryForObject(sql, mapper, name);
        if (role != null)
            return Optional.of(role);
        return Optional.empty();
    }
    public Optional<Role> findById(Long id){
        String sql = "SELECT * FROM roles r WHERE r.id = ?";
        RowMapper<Role> mapper = (resultSet, rowNum) -> {
            return new Role(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"));
        };
        Role role = jdbcTemplate.queryForObject(sql, mapper, id);
        if (role != null)
            return Optional.of(role);
        return Optional.empty();
    }
}
