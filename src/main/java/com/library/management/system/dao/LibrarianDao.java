package com.library.management.system.dao;

import java.util.List;

import com.library.management.system.model.Librarian;

public interface LibrarianDao {
	
	public void add(Librarian librarian);
	
	public void update(Librarian librarian);
	
	public List<Librarian> findAll();
	
	public Librarian findById(Integer id);
	
	public Librarian findByEmail(String email);
	
	public List<Librarian> findByName(String name);
	
}
