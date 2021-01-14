package com.unibuc.Home.Management.Platform.repository;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.domain.Task;
import com.unibuc.Home.Management.Platform.domain.Status;
import com.unibuc.Home.Management.Platform.exception.PersonNotFoundException;
import com.unibuc.Home.Management.Platform.exception.TaskNotFoundException;
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
public class TaskRepository {
    private JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> getAll() {
        String sql = "select * from tasks";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priority(resultSet.getString("priority"))
                .status(Status.valueOf(resultSet.getString("status")))
                .startDate(resultSet.getDate("startDate"))
                .endDate(resultSet.getDate("endDate"))
                .personId(resultSet.getInt("personId"))
                .build();

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Task> getTaskById(Long id) {
        String sql = "SELECT * FROM tasks t WHERE t.id = ?";
        RowMapper<Task> mapper = (resultSet, rowNum) -> {
            return new Task(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getString("priority"),
                    Status.valueOf(resultSet.getString("status")),
                    resultSet.getDate("startDate"),
                    resultSet.getDate("endDate"),
                    resultSet.getLong("personId"));
        };
        Task task = jdbcTemplate.queryForObject(sql, mapper, id);
        if (task != null)
            return Optional.of(task);
        return Optional.empty();
    }

    public void deleteTaskById(Long id) {
        String sql="delete from tasks  where  id = ?";
        jdbcTemplate.update(sql,id);
    }

    public void changePersonId(Long id, Long personId) {
        String sql = "update tasks t set t.personId = ? where t.id = ?";
        int numberOfUpdatedPersonTasks = jdbcTemplate.update(sql, personId, id);
        if (numberOfUpdatedPersonTasks == 0) {
            throw new RuntimeException();
        }
    }

    public Task createTask(Task task) {
        String sql = "insert into tasks values (?,?,?,?,?,?,?,?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getPriority());
            preparedStatement.setString(5, task.getStatus().toString());
            preparedStatement.setDate(6, (Date) task.getStartDate());
            preparedStatement.setDate(7, (Date) task.getEndDate());
            preparedStatement.setLong(8, task.getPersonId());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        generatedKeyHolder.getKey();
        task.setId(generatedKeyHolder.getKey().longValue());
        return task;
    }

    public List<Task> getTaskByPersonName(String firstName) {
        String sql = "select * from tasks t inner join persons p on t.personId = p.Id where p.firstName = ?";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priority(resultSet.getString("priority"))
                .status(Status.valueOf(resultSet.getString("status")))
                .startDate(resultSet.getDate("startDate"))
                .endDate(resultSet.getDate("endDate"))
                .personId(resultSet.getInt("personId"))
                .build();
        List<Task> listOfTasks = jdbcTemplate.query(sql, rowMapper, firstName);
        if(listOfTasks.size() == 0)
            throw new PersonNotFoundException(firstName);
        return listOfTasks;
    }

    public List<Task> getFreeTasks() {
        String sql = "select * from tasks where personId is null";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priority(resultSet.getString("priority"))
                .status(Status.valueOf(resultSet.getString("status")))
                .startDate(resultSet.getDate("startDate"))
                .endDate(resultSet.getDate("endDate"))
                .personId(resultSet.getInt("personId"))
                .build();

        return jdbcTemplate.query(sql, rowMapper);
    }

    public void changeTaskStatus(Long taskId, String status, Long personId) {
        if(contains(status) == true)
        {
            String sql = "update tasks t set t.status = ? where t.id = ? and t.personId = ?";
            int numberOfUpdatedPersonTasks = jdbcTemplate.update(sql, status, taskId, personId);
            if (numberOfUpdatedPersonTasks == 0) {
                throw new RuntimeException();
            }
        }
        else
        {
            throw new TaskNotFoundException(status);
        }

    }
    public static boolean contains(String status){
        for (Status s : Status.values())
        {
            if(s.name().equals(status)){
                return true;
            }
        }
        return false;
    }
}
