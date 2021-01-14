package com.unibuc.Home.Management.Platform.service;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.dto.PersonDto;
import com.unibuc.Home.Management.Platform.repository.PersonRepository;
import org.hibernate.hql.internal.ast.ParseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    //private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getById(Long id) {
        Optional<Person> bankAccountOptional = personRepository.getById(id);
        if(bankAccountOptional.isPresent()) {
            return bankAccountOptional.get();
        } else {
            throw new RuntimeException();
        }
    }

    public List<Person> getAll()
    {
        return personRepository.getAll();
    }

    public void changeYear(Person person) {
        personRepository.updatePersonDetails(
                person.getId(), person.getAge());
    }
    public Person createPerson(Person person){
        return personRepository.createPerson(person);
    }

    public void deletePersonById(Long id){
        personRepository.deletePersonById(id);
    }

 /*   public PersonDto save(PersonDto personDto) {
        Person person = mapToEntity(personDto);
        Person savedPerson = personRepository.save(person);
        return mapToDto(savedPerson);
    }

    public PersonDto update(PersonDto personDto) {
        Person update = personRepository.update(mapToEntity((personDto)));
        if (update != null) {
            return mapToDto(update);
        }
        return null;
    }

    public List<PersonDto> getAll() {
        return personRepository.getAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    //    public void delete(PersonDto person) {
//        personRepository.delete(mapToEntity(person));
//    }
    public boolean delete(String name) {
        Optional<Person> optionalPerson = personRepository.getAll()
                .stream()
                .filter(person -> person.getFirstName().equals(name))
                .findAny();
        if (optionalPerson.isPresent()) {
            personRepository.delete(optionalPerson.get());
            return true;
        }
        return false;
    }

    private Person mapToEntity(PersonDto personDto) {
        return Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .age(personDto.getAge())
                .build();
    }

    private PersonDto mapToDto(Person person) {
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .age(person.getAge())
                .build();

    }*/
}
