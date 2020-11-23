package com.unibuc.Home.Management.Platform.service;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.dto.PersonDto;
import com.unibuc.Home.Management.Platform.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(PersonDto personDto)
    {  Person person = mapToEntity(personDto);
        return personRepository.save(person);
    }

    public List<PersonDto> getAll(){
        return personRepository.getAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void delete (PersonDto person){
        personRepository.delete(mapToEntity(person));
    }

    private Person mapToEntity(PersonDto personDto) {
        return Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .age(personDto.getAge())
                .build();
    }

    private PersonDto mapToDto(Person person)
    {
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getFirstName())
                .age(person.getAge())
                .build();

    }
}
