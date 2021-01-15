package com.unibuc.demo.service;

import com.unibuc.demo.domain.Person;
import com.unibuc.demo.domain.Role;
import com.unibuc.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @Test
    public void createPersonHappyFlow(){
        //arrange
        Role role = new Role();
        role.setId(1L);
        role.setName("ADMIN");
        role.setDescription("ADMIN ROLE");

        Person person = new Person("Cristi","", 20, role.getId());
        Person savedPerson = new Person(1,"Cristi","", 20, role.getId());

        when(personRepository.createPerson(person)).thenReturn(savedPerson);
        //act
        Person result = personService.createPerson(person);

        //assert
        assertEquals(person.getAge(), result.getAge());
        assertEquals(person.getFirstName(), result.getFirstName());
        assertEquals(person.getLastName(), result.getLastName());
        assertEquals(savedPerson.getRoleId(), result.getRoleId());
    }

}
