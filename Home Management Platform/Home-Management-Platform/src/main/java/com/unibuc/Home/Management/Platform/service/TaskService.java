package com.unibuc.Home.Management.Platform.service;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.domain.Task;
import com.unibuc.Home.Management.Platform.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    public Task getById(Long id) {
        Optional<Task> taskOptional = taskRepository.getTaskById(id);
        if(taskOptional.isPresent()) {
            return taskOptional.get();
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteTaskById(id);
    }

    public void changePersonId(Long id, Long personId) {
        taskRepository.changePersonId(id, personId);
    }

    public Task createTask(Task task) {
        return taskRepository.createTask(task);
    }

    public List<Task> getTaskByPersonName(String nameOfPerson) {
        return taskRepository.getTaskByPersonName(nameOfPerson);
    }

    public List<Task> getEmptyTask() {
        return taskRepository.getEmptyTask();
    }

    public void changeTaskStatus(Long taskId, String status, Long personId) {
        taskRepository.changeTaskStatus(taskId,status,personId);
    }

    public List<Task> getByTaskName(String nameOfTask) {
        return taskRepository.getByTaskName(nameOfTask);
    }

    public List<Task> getOwnTasks(Principal principal) {
        return taskRepository.getOwnTasks(principal);
    }
}
