package com.webapp.webapp.dao.user;

import java.util.Optional;

import com.webapp.webapp.model.user.User;

public interface UserDao {

    Optional<User> findByEmail(String email);

}
