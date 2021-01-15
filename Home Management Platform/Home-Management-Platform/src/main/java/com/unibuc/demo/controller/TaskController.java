package com.unibuc.demo.controller;

import com.unibuc.demo.domain.Task;
import com.unibuc.demo.mapper.TaskMapper;
import com.unibuc.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    private TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getByName/{nameOfPerson}")
    public List<Task> getTaskByPersonName(@PathVariable String nameOfPerson) {
        return taskService.getTaskByPersonName(nameOfPerson);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getOwnTasks")
    public List<Task> getOwnTasks(Principal principal){
        return taskService.getOwnTasks(principal);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getByTaskName/{nameOfTask}")
    public List<Task> getByTaskName(@PathVariable String nameOfTask) {
        return taskService.getByTaskName(nameOfTask);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/getEmptyTask")
    public List<Task> getEmptyTask() {
        return taskService.getEmptyTask();
    }
    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody Task task) {
        // Person person = personMapper.personDtoToPerson(personDto);  TO DO
        Task createdTask = taskService.createTask(task);
        return ResponseEntity
                .created(URI.create("/tasks/" + createdTask.getId()))
                .body(createdTask);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}/{personId}")
    public void changePersonId(@PathVariable("id") Long id,@PathVariable("personId") Long personId) {
        taskService.changePersonId(id, personId);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/changeTaskStatus/{taskId}/{status}/{personId}")
    public void changeTaskStatus(@PathVariable("taskId") Long taskId,@PathVariable("status") String status
            ,@PathVariable("personId") Long personId) {
        taskService.changeTaskStatus(taskId, status.toUpperCase(),personId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable("id") Long id){
        taskService.deleteTaskById(id);
    }
}
