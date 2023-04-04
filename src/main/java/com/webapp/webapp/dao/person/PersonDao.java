package com.webapp.webapp.dao.person;

import java.util.UUID;

import com.webapp.webapp.model.person.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(UUID id);

    Optional<Person> selectPersonByName(String Name);

    int deletePersonById(UUID id);

    int deletePersonByName(String Name);

    int updatePersonById(Person person);

}
