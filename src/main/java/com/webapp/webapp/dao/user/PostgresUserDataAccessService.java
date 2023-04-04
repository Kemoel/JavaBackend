package com.webapp.webapp.dao.user;

import org.springframework.stereotype.Repository;

import com.webapp.webapp.model.user.Role;
import com.webapp.webapp.model.user.User;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Repository("postgresUser")
public class PostgresUserDataAccessService implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresUserDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String sql = "SELECT id, firstname, lastname, email, password, role FROM user WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, 
            (resultSet, i) -> {
                Integer id = Integer.parseInt(resultSet.getString("id"));
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String userEmail = resultSet.getString("email");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role"));
                return new User(id, firstname, lastname, userEmail, password, role);
            },
            new Object[]{email} 
        );
        return Optional.ofNullable(user);
    }
    
}
