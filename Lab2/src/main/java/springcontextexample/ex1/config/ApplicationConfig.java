package springcontextexample.ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springcontextexample.ex1.domain.Student;

@Configuration
public class ApplicationConfig {
    @Bean
    @Primary
    public Student student(){
        return new Student();
    }

//    @Bean(value = "silvia")
//    public Student anotherStudent() {
//        return new Student("Silvia");
//    }
//
//    @Bean(value = "john")
//    public Student yetAnotherStudent() {
//        return new Student("John");
//    }
//    @Bean("harry")
//    public Student AnotherStudentAgain() {
//        return new Student("Harry");
//    }
}
