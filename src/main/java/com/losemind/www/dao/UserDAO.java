package com.losemind.www.dao;

import java.util.List;

import com.losemind.www.model.User;

public interface UserDAO  {
	
    void addUser(User user);
    void updateUser(User user);
    void removeUser(User user);
    List<User> listUsers(int pageNumber, int perPage, String criteria);
    User getById(int id);
    public int getCurrentDBsize();
}
