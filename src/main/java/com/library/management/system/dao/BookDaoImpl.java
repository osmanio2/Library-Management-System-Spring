package com.library.management.system.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.library.management.system.model.Book;
import com.library.management.system.service.CustomSessionFactory;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	

	@Qualifier("customSessionFactory")
	@Autowired
	private CustomSessionFactory customSessionFactory;
	
	@Override
	public void add(Book book) {
		Session session = customSessionFactory.getSessionFactory().openSession();
		session.save(book);
		session.close();
	}
	
	@Override
	public void update(Book book) {
		Session session = customSessionFactory.getSessionFactory().openSession();
		session.update(book);
		session.close();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		Session session = customSessionFactory.getSessionFactory().openSession();
		List<Book> books = (List<Book>) session.createQuery("FROM Book").list();
		session.close();
		return books;
	}
	
	@Override
	public Book findByISBN(String ISBN) {
		Session session = customSessionFactory.getSessionFactory().openSession();
		Book book = (Book) session.get(Book.class, ISBN);
		session.close();
		return book;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findByName(String name) {
		Session session = customSessionFactory.getSessionFactory().openSession();
		List<Book> books = (List<Book>) session
				.createQuery("SELECT p FROM Book p WHERE p.title LIKE :name")
				.setParameter("name", name).list();
		session.close();
		return books;
	}
 
   
}
