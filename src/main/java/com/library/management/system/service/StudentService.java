package com.library.management.system.service;

import javax.security.sasl.AuthenticationException;

import com.library.management.system.model.Student;

public interface StudentService {
	public Student getStudentDetials() throws AuthenticationException;
}
