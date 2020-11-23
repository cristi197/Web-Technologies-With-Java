package com.unibuc.Home.Management.Platform.repository;

import com.unibuc.Home.Management.Platform.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> persons = new ArrayList<>();
    public Person save(Person person)
    {
        persons.add(person);
        return person;
    }

    public List<Person> getAll(){
        return persons;
    }
    public void delete (Person person){
        persons.remove(person);
    }

}
