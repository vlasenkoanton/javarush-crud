package com.losemind.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.losemind.www.dao.UserDAO;
import com.losemind.www.model.User;

@Service
public class UserServiceImpl implements UserService {
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void addUser(User user) {
		this.userDAO.addUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		this.userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public void removeUser(User user) {
		this.userDAO.removeUser(user);
	}

	@Override
	@Transactional
	public User getById(int id) {
		return this.userDAO.getById(id);
	}

	@Override
	@Transactional
	public List<User> listUsers(int pageNumber, int perPage, String criteria) {
		return this.userDAO.listUsers(pageNumber, perPage, criteria);
	}

	@Override
	public int getNumberOfPages() {
		return (int) Math.ceil((double) this.userDAO.getCurrentDBsize() / ENTITIES_PER_PAGE);
	}
	
	
}
