package com.revature.data.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Events;

public class EventDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, 
			propagation = Propagation.REQUIRED, 
			rollbackFor = Exception.class)
	public void create(Events event) {
		sessionFactory.getCurrentSession().save(event);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, 
			propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void update(Events event) {
		sessionFactory.getCurrentSession().saveOrUpdate(event);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, 
			propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void delete(Events event) {
		sessionFactory.getCurrentSession().delete(event);
	}

	public Events findById(int eventId){
		String queryString = "FROM Events WHERE eventId = :eventId";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setInteger("eventId", eventId);
		Object result = query.uniqueResult();
		return (Events)result;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Events> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM Events").list();
	}
}
