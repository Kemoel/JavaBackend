package com.webapp.webapp.dao;

import com.webapp.webapp.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("postgres")
public class PostgresDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String sql = "INSERT INTO person (id, name) VALUES ('"+id+"','"+person.getName()+"')";
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name FROM person";
        List<Person> people = jdbcTemplate.query(sql, (resultSet, i) -> {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                return new Person(id, name);
            }
        );
        return people;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql, 
            (resultSet, i) -> {
                UUID personId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                return new Person(personId, name);
            },
            new Object[]{id} 
        );
        return Optional.ofNullable(person);
    }

    @Override
    public Optional<Person> selectPersonByName(String name) {
        final String sql = "SELECT id, name FROM person WHERE name = ?";
        Person person = jdbcTemplate.queryForObject(sql, 
            (resultSet, i) -> {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String personName = resultSet.getString("name");
                return new Person(id, personName);
            },
            new Object[]{name} 
        );
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "DELETE FROM person WHERE id = '"+id+"'";
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public int deletePersonByName(String name) {
        final String sql = "DELETE FROM person WHERE name = '"+name+"'";
        jdbcTemplate.update(sql);
        return 0;
    }

    @Override
    public int updatePersonById(Person person) {
        final String sql = "UPDATE person SET name = '"+person.getName()+"' WHERE id = '"+person.getId()+"'";
        jdbcTemplate.update(sql);
        return 0;
    }

}
