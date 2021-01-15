package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Task;
import com.unibuc.demo.dto.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public static Task taskDtoToTask(TaskDto taskDto){
        return new Task(taskDto.getName(),taskDto.getDescription(),taskDto.getPriorityId(),
                taskDto.getStatus(),taskDto.getStartDate(),taskDto.getEndDate(),taskDto.getPersonId());
    }
}
