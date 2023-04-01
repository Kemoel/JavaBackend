package com.webapp.webapp.dao;

import com.webapp.webapp.model.Person;

import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository("fakeDao")
public class LocalPersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person){
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople(){
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
            .filter(person -> person.getId().equals(id))
            .findFirst();
    }

    @Override
    public Optional<Person> selectPersonByName(String name) {
        return DB.stream()
            .filter(person -> person.getName().equals(name))
            .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int deletePersonByName(String name) {
        return 1;
    }

    @Override
    public int updatePersonById(Person update) {
        return selectPersonById(update.getId())
            .map(person -> {
                int indexOfPersonToUpdate = DB.indexOf(person);
                if(indexOfPersonToUpdate >= 0){
                    DB.set(indexOfPersonToUpdate, new Person(update.getId(), update.getName()));
                    return 1;
                }
                return 0;
            })
            .orElse(0);
    }

}
