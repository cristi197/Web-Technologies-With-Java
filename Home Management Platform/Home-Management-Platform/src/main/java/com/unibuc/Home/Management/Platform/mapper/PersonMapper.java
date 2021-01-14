package com.unibuc.Home.Management.Platform.mapper;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person personDtoToPerson(PersonDto personDto){
        return new Person(personDto.getFirstName(),personDto.getLastName(),
                personDto.getAge(),personDto.getRoleId());
    }
}
