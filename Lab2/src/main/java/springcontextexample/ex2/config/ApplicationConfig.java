package springcontextexample.ex2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "springcontextexample.ex2.domain")
public class ApplicationConfig {
}
