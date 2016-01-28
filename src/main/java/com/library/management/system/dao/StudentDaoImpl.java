package com.library.management.system.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.library.management.system.model.Student;
import com.library.management.system.service.CustomSessionFactory;
import com.library.management.system.service.CustomSessionFactoryImpl;


@Repository("studentDao")
public class StudentDaoImpl implements StudentDao{
	private final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
	public StudentDaoImpl() {
		logger.debug("initialising Student repository");
	}
	
	@Qualifier("customSessionFactory")
	@Autowired
	private CustomSessionFactory customSessionFactory;
	

	 
	@Override
	public void add(Student student) {
		customSessionFactory.getSessionFactory().openSession().save(student);
	}
	
	@Override
	public void update(Student student) {
		customSessionFactory.getSessionFactory().openSession().update(student);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Student> findAll() {
		return (List<Student>) customSessionFactory.getSessionFactory().openSession()
				.createQuery("FROM Student").list();
	}
	
	@Override
	public Student findById(Integer id) {
		return (Student) customSessionFactory.getSessionFactory().openSession().get(Student.class, id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Student findByEmail(String email) {
		if(customSessionFactory == null) {
			customSessionFactory = new CustomSessionFactoryImpl();
		}
		List values = customSessionFactory.getSessionFactory().openSession()
				.createQuery("SELECT p FROM Student p WHERE p.studEmail = :email")
				.setParameter("email", email).list();
		return (Student) ((!values.isEmpty()) ? values.get(0) : null);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Student> findByName(String name) {
		return (List<Student>)  customSessionFactory.getSessionFactory().openSession()
		.createQuery("SELECT p FROM Student p WHERE p.studName LIKE :name")
		.setParameter("name", name).list();
	}
}
