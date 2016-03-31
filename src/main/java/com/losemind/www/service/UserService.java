package com.losemind.www.service;

import java.util.List;

import com.losemind.www.model.User;

public interface UserService {
	
	public static final int ENTITIES_PER_PAGE = 5;
	
    void addUser(User user);
    void updateUser(User user);
    void removeUser(User user);
    public List<User> listUsers(int pageNumber, int perPage, String criteria);
    public User getById(int id);
    public int getNumberOfPages();
}
