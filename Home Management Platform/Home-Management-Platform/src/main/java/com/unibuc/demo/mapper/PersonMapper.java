package com.unibuc.demo.mapper;


import com.unibuc.demo.domain.Person;
import com.unibuc.demo.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person personDtoToPerson(PersonDto personDto){
        return new Person(personDto.getFirstName(),personDto.getLastName(),
                personDto.getAge(),personDto.getRoleId());
    }
}
