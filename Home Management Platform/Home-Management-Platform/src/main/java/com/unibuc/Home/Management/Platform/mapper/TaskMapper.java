package com.unibuc.Home.Management.Platform.mapper;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.domain.Task;
import com.unibuc.Home.Management.Platform.dto.PersonDto;
import com.unibuc.Home.Management.Platform.dto.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task taskDtoToTask(TaskDto taskDto){
        return new Task(taskDto.getName(),taskDto.getDescription(),taskDto.getPriorityId(),
                taskDto.getStatus(),taskDto.getStartDate(),taskDto.getEndDate(),taskDto.getPersonId());
    }
}
