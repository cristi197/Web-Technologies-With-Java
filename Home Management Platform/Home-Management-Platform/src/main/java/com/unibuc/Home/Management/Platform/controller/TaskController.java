package com.unibuc.Home.Management.Platform.controller;

import com.unibuc.Home.Management.Platform.domain.Task;
import com.unibuc.Home.Management.Platform.mapper.TaskMapper;
import com.unibuc.Home.Management.Platform.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/getByName/{nameOfPerson}")
    public List<Task> getTaskByPersonName(@PathVariable String nameOfPerson) {
        return taskService.getTaskByPersonName(nameOfPerson);
    }
    @GetMapping("/getFreeTasks")
    public List<Task> getFreeTasks() {
        return taskService.getFreeTasks();
    }
    @PostMapping
    public ResponseEntity<Task> createTask(
            @RequestBody Task task) {
        // Person person = personMapper.personDtoToPerson(personDto);  TO DO
        Task createdTask = taskService.createTask(task);
        return ResponseEntity
                .created(URI.create("/bankAccount/" + createdTask.getId()))
                .body(createdTask);
    }
    @PutMapping("/{id}/{personId}")
    public void changePersonId(@PathVariable("id") Long id,@PathVariable("personId") Long personId) {
        taskService.changePersonId(id, personId);
    }
    @PutMapping("/changeTaskStatus/{taskId}/{status}/{personId}")
    public void changeTaskStatus(@PathVariable("taskId") Long taskId,@PathVariable("status") String status
            ,@PathVariable("personId") Long personId) {
        taskService.changeTaskStatus(taskId, status.toUpperCase(),personId);
    }
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable("id") Long id){
        taskService.deleteTaskById(id);
    }
}
