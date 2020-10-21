package springcontextexample.ex2.domain;

import org.springframework.stereotype.Component;

@Component
public class  ScheduleService {

    public String scheduleClass(String nameClass, Person person){
        //to do login for scheduling classes
    return String.format("Class %s was scheduled for: %s",nameClass,person.getName());
    }
}
