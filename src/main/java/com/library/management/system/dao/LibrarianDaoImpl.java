package com.library.management.system.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.library.management.system.model.Librarian;
import com.library.management.system.service.CustomSessionFactory;

@Repository("librarianDao")
public class LibrarianDaoImpl implements LibrarianDao{
	
	@Qualifier("customSessionFactory")
	@Autowired
	private CustomSessionFactory customSessionFactory;
	
	@Override
	public void add(Librarian librarian) {
		customSessionFactory.getSessionFactory().openSession().save(librarian);
	}
	
	@Override
	public void update(Librarian librarian) {
		customSessionFactory.getSessionFactory().openSession().update(librarian);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Librarian> findAll() {
		return (List<Librarian>) customSessionFactory.getSessionFactory().openSession()
				.createQuery("FROM Librarian").list();
	}
	
	@Override
	public Librarian findById(Integer id) {
		return (Librarian) customSessionFactory.getSessionFactory().openSession().get(Librarian.class, id);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Librarian findByEmail(String email) {
		
		List values = customSessionFactory.getSessionFactory().openSession()
				.createQuery("SELECT p FROM Librarian p WHERE p.librnEmail = :email")
				.setParameter("email", email).list();
		return (Librarian) ((!values.isEmpty()) ? values.get(0) : null);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Librarian> findByName(String name) {
		return (List<Librarian>) customSessionFactory.getSessionFactory().openSession()
		.createQuery("SELECT p FROM Librarian p WHERE p.librnName LIKE :name")
		.setParameter("name", name).list();
	}
}
