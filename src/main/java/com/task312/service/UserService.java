package com.task312.service;


import com.task312.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    List<User> getUserList();


    public User showUserById(long id);

    public boolean updateUser(User user);

    public boolean addUser(User user);

    void deleteUser(long id);
}
