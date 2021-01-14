package com.unibuc.Home.Management.Platform.controller;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.dto.PersonDto;
import com.unibuc.Home.Management.Platform.mapper.PersonMapper;
import com.unibuc.Home.Management.Platform.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;
    private PersonMapper personMapper;

    public PersonController(PersonService personService) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Person getBankAccount(@PathVariable Long id) {
        return personService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Person> createBankAccount(
            @RequestBody Person person) {
       // Person person = personMapper.personDtoToPerson(personDto);  TO DO
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity
                .created(URI.create("/bankAccount/" + createdPerson.getId()))
                .body(createdPerson);
    }

    @PutMapping
    public void changeYear(
            @RequestBody
                    Person person) {
        personService.changeYear(person);
    }
    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable("id") Long id){
        personService.deletePersonById(id);
    }
}
//    @RequestMapping(value = "/test/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String test(@PathVariable("name") String name) {
//        return "Hello from " + name;
//    }

//    @RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String test(@RequestParam String name) {
//        return "Hello from " + name;
//    }
/*    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getAll() {
        return personService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto create(@RequestBody PersonDto personDto) {
        return personService.save(personDto);
    }

    @DeleteMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@PathVariable("name") String name) {
        boolean result = personService.delete(name);
        return result ? String.format("Person %s was removed", name) : String.format("Person %s was not removed", name);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto update(@RequestBody PersonDto personDto) {
        return personService.update(personDto);
    }*/
