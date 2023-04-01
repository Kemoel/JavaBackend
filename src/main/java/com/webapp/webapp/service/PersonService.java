package com.webapp.webapp.service;

import com.webapp.webapp.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.webapp.webapp.dao.PersonDao;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao){
        this.personDao = personDao;
    }

    public int insertPerson(Person person){
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public Optional<Person> getPersonByName(String name){
        return personDao.selectPersonByName(name);
    }

    public int deletePersonById(UUID id){
        return personDao.deletePersonById(id);
    }

    public int deletePersonByName(String name){
        return personDao.deletePersonByName(name);
    }

    public int updatePersonById(Person newPerson){
        return personDao.updatePersonById(newPerson);
    }

}
