package com.ub.webtechnologies.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.ub.webtechnologies.service","com.ub.webtechnologies.aspects"})
@EnableAspectJAutoProxy
public class ApplicationConfiguration {
}
