package com.webapp.webapp.api;

import com.webapp.webapp.service.PersonService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.webapp.webapp.model.Person;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person){
        personService.insertPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "byId")
    public Person getPersonById(@Valid @NonNull @RequestBody Person person){
        return personService.getPersonById(person.getId())
            .orElse(null);
    }

    @GetMapping(path = "byName")
    public Person getPersonByname(@Valid @NonNull @RequestBody Person person){
        return personService.getPersonByName(person.getName())
            .orElse(null);
    }

    @DeleteMapping(path = "byId")
    public void deletePersonById(@Valid @NonNull @RequestBody Person person){
        personService.deletePersonById(person.getId());
    }

    @DeleteMapping(path = "byName")
    public void deletePersonByName(@Valid @NonNull @RequestBody Person person){
        personService.deletePersonByName(person.getName());
    }

    @PutMapping(path = "byId")
    public void updatePersonById(@Valid @NonNull @RequestBody Person personToUpdate){
        personService.updatePersonById(personToUpdate);
    }

}