package com.library.management.system.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.library.management.system.dao.StudentDao;
import com.library.management.system.model.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	@Override
	public Student getStudentDetials() throws AuthenticationException {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.isAuthenticated()) {
			throw new AuthenticationException("Student authentication failure!");
		}
		return studentDao.findByEmail(auth.getName());
	}

}
