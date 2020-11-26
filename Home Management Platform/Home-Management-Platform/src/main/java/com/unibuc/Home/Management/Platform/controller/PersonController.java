package com.unibuc.Home.Management.Platform.controller;

import com.unibuc.Home.Management.Platform.dto.PersonDto;
import com.unibuc.Home.Management.Platform.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

//    @RequestMapping(value = "/test/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String test(@PathVariable("name") String name) {
//        return "Hello from " + name;
//    }

    //    @RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String test(@RequestParam String name) {
//        return "Hello from " + name;
//    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
    }

}
