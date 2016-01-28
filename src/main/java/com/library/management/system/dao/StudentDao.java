package com.library.management.system.dao;

import java.util.List;

import com.library.management.system.model.Student;

public interface StudentDao {
	
	public void add(Student student);
	
	public void update(Student student);
	
	public List<Student> findAll();
	
	public Student findById(Integer id);
	
	public Student findByEmail(String email);
	
	public List<Student> findByName(String name);
	
}
