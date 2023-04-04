package com.webapp.webapp.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.webapp.webapp.dao.person.PersonDao;
import com.webapp.webapp.model.person.Person;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("postgresPerson") PersonDao personDao){
        this.personDao = personDao;
    }

    public int insertPerson(Person person){
        try {
            getPersonByName(person.getName());
        } catch (Exception e){
            return personDao.insertPerson(person);
        }
        throw new IllegalStateException("name taken");
    }

    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        try {
            personDao.selectPersonById(id);
        } catch (Exception e){
            throw new IllegalStateException("id does not exist");
        }
        return personDao.selectPersonById(id);
    }

    public Optional<Person> getPersonByName(String name){
        try {
            personDao.selectPersonByName(name);
        } catch (Exception e){
            throw new IllegalStateException("name does not exist");
        }
        return personDao.selectPersonByName(name);
    }

    public int deletePersonById(UUID id){
        try {
            personDao.deletePersonById(id);
        } catch (Exception e){
            throw new IllegalStateException("id does not exist");
        }
        return personDao.deletePersonById(id);
    }

    public int deletePersonByName(String name){
        try {
            personDao.deletePersonByName(name);
        } catch (Exception e){
            throw new IllegalStateException("name does not exist");
        }
        return personDao.deletePersonByName(name);
    }

    public int updatePersonById(Person newPerson){
        try {
            personDao.updatePersonById(newPerson);
        } catch (Exception e){
            throw new IllegalStateException("id does not exist");
        }
        return personDao.updatePersonById(newPerson);
    }

}
