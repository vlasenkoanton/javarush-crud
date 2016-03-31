package com.losemind.www.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.losemind.www.model.User;
import com.losemind.www.service.UserService;

@Repository
public class UserDAOImpl implements UserDAO {
	private int currentDBsize;
	
	@Override
	public int getCurrentDBsize() {
		return currentDBsize;
	}

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
		System.out.println("New User added successfully, id: " + user.getId());
	}

	@Override
	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		System.out.println("User updated successfully, id: " + user.getId());
	}

	@Override
	public void removeUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(user);
		System.out.println("User deleted, id:" + user.getId());
	}

	@Override
	public User getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		System.out.println("Getting user by id successful");
		return user;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUsers(int pageNumber, int perPage, String criteria) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Query hibernateQuery;
		//if search criteria available form list considering it
		//otherwise list off all database entities
		if (criteria != null && !criteria.equals("")) {
			FullTextSession fts = Search.getFullTextSession(session);
			try {
				fts.createIndexer().startAndWait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			QueryBuilder qb = fts.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();

			org.apache.lucene.search.Query query = qb.keyword().onFields("name", "age").matching(criteria)
					.createQuery();
			hibernateQuery = fts.createFullTextQuery(query, User.class);			
		} else {
			hibernateQuery = session.createQuery("from User");
		}
		//need to define current number of pages		
		currentDBsize = hibernateQuery.list().size();
		
		if (pageNumber < 1) {
			pageNumber = 1;
		}

		hibernateQuery.setFirstResult((pageNumber - 1) * UserService.ENTITIES_PER_PAGE);
		hibernateQuery.setMaxResults(perPage);		
		
		return hibernateQuery.list();
	}	

}
