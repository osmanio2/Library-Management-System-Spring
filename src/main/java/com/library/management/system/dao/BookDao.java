package com.library.management.system.dao;

import java.util.List;

import com.library.management.system.model.Book;

public interface BookDao {
	
	public void add(Book book);
	
	public void update(Book book);
	
	public List<Book> findAll();
	
	public Book findByISBN(String ISBN);
	
	public List<Book> findByName(String name);
	
}
