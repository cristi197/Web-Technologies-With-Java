package springcontextexample.ex1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcontextexample.ex1.config.ApplicationConfig;
import springcontextexample.ex1.domain.Student;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        Student student =  context.getBean(Student.class);
        student.setName("Cristian");
        System.out.println(student.getName());
//        Student anotherStudent = context.getBean("silvia", Student.class);
//        Student yetAnotherStudent = context.getBean("john", Student.class);
//        Student anotherStudentAgain = context.getBean("harry", Student.class);
//
//
//        System.out.println(anotherStudent.getName());
//        System.out.println(yetAnotherStudent.getName());
//        System.out.println(anotherStudentAgain.getName());
    }
}
