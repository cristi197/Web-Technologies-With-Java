package com.unibuc.Home.Management.Platform.repository;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.domain.Task;
import com.unibuc.Home.Management.Platform.domain.Status;
import com.unibuc.Home.Management.Platform.domain.User;
import com.unibuc.Home.Management.Platform.exception.PersonNotFoundException;
import com.unibuc.Home.Management.Platform.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Repository
public class TaskRepository {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonRepository personRepository;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> getAll() {
        String sql = "select * from tasks";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priorityId(resultSet.getLong("priorityId"))
                .status(Status.valueOf(resultSet.getString("status")))
                .startDate(resultSet.getDate("startDate"))
                .endDate(resultSet.getDate("endDate"))
                .personId(resultSet.getInt("personId"))
                .build();
        //sendEmail();
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Task> getTaskById(Long id) {
        String sql = "SELECT * FROM tasks t WHERE t.id = ?";
        RowMapper<Task> mapper = (resultSet, rowNum) -> {
            return new Task(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getLong("priorityId"),
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
            preparedStatement.setLong(4, task.getPriorityId());
            preparedStatement.setString(5, task.getStatus().toString());
            preparedStatement.setDate(6, (Date) task.getStartDate());
            preparedStatement.setDate(7, (Date) task.getEndDate());
            preparedStatement.setObject(8, task.getPersonId());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        generatedKeyHolder.getKey();
        task.setId(generatedKeyHolder.getKey().longValue());
        return task;
    }

    public List<Task> getTaskByPersonName(String firstName) {
        String sql = "select * from tasks t inner join person p on t.personId = p.Id where p.firstName = ?";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priorityId(resultSet.getLong("priorityId"))
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
    public List<Task> getByTaskName(String nameOfTask) {

        String sql = "select * from tasks t inner join person p on t.personId = p.Id where" +
                " t.Name like  ?";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priorityId(resultSet.getLong("priorityId"))
                .status(Status.valueOf(resultSet.getString("status")))
                .startDate(resultSet.getDate("startDate"))
                .endDate(resultSet.getDate("endDate"))
                .personId(resultSet.getInt("personId"))
                .build();
        List<Task> listOfTasks = jdbcTemplate.query(sql, rowMapper, nameOfTask);
        if(listOfTasks.size() == 0)
            throw new TaskNotFoundException();
        return listOfTasks;
    }

    public List<Task> getEmptyTask() {
        String sql = "select * from tasks where personId is null";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priorityId(resultSet.getLong("priorityId"))
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

    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("cristian.teodorescu97@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");


        javaMailSender.send(msg);

    }


    public List<Task> getOwnTasks(Principal principal) {
        String username = principal.getName();
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Person> person = personRepository.getById(user.get().getPersonId());
        String sql = "select * from tasks t inner join person p on t.personId = p.Id where" +
                " p.id=?";
        RowMapper<Task> rowMapper = (resultSet, rowNo) -> Task.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .priorityId(resultSet.getLong("priorityId"))
                .status(Status.valueOf(resultSet.getString("status")))
                .startDate(resultSet.getDate("startDate"))
                .endDate(resultSet.getDate("endDate"))
                .personId(resultSet.getInt("personId"))
                .build();
        List<Task> listOfTasks = jdbcTemplate.query(sql, rowMapper, person.get().getId());
        if(listOfTasks.size() == 0)
            throw new TaskNotFoundException();
        return listOfTasks;
    }
}
