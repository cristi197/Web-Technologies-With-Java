package springcontextexample.ex2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcontextexample.ex2.config.ApplicationConfig;
import springcontextexample.ex2.domain.Person;
import springcontextexample.ex2.domain.ScheduleService;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        ScheduleService scheduleService =  context.getBean(ScheduleService.class);
        System.out.println(scheduleService.scheduleClass("web programming with Java",new Person("Silvia")));
    }
}
