package com.library.management.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.management.system.dao.LibrarianDao;
import com.library.management.system.dao.StudentDao;
import com.library.management.system.model.Librarian;
import com.library.management.system.model.Student;

@Service("authService")
public class AuthServiceImpl implements AuthService, UserDetailsService {

	//get Student from the database, via Hibernate
	@Qualifier("studentDao")
	@Autowired
	private StudentDao studentDao;

	//get Librarian from the database, via Hibernate
	@Qualifier("librarianDao")
	@Autowired
	private LibrarianDao librarianDao;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
		Student student = studentDao.findByEmail(username);
		Librarian librarian = librarianDao.findByEmail(username);;
		List<GrantedAuthority> authorities = new ArrayList<>();
		com.library.management.system.model.User user = new com.library.management.system.model.User();
		if(student != null) {
			user.setEmail(student.getStudEmail());
			user.setPassword(student.getStudPassword());
			user.setType(student.getUserType());
		} else if (librarian != null) {
			user.setEmail(librarian.getLibrnEmail());
			user.setPassword(librarian.getLibrnPassword());
			user.setType(librarian.getUserType());
		} else {
			throw new UsernameNotFoundException("This email does not exist");
		}
		
			authorities.add(new SimpleGrantedAuthority(user.getType().toString()));
			return buildUserForAuthentication(user, authorities);
		
		
	}

	// Converts Users (student/librarian) to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.library.management.system.model.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getEmail(), user.getPassword(), 
			true, true, true, true, authorities);
	}

	
}
