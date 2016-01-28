package com.library.management.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.library.management.system.type.UserType;

@Entity
@Table(name = "librariant_member")
public class Librarian {
	
	@Id 
	@Column(name = "LIBRN_ID")
	@GeneratedValue
	private int id;
	
	@Column(name = "LIBRN_NAME")
	private String librnName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "USER_TYPE")
	private UserType userType;
			
    @Column(name = "LIBRN_PASSWORD")
    private String librnPassword;
	
    @Column(name = "LIBRN_PHONE")
    private String librnYear;
    
    @Column(name = "LIBRN_EMAIL")
    private String librnEmail;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the librnName
	 */
	public String getLibrnName() {
		return librnName;
	}

	/**
	 * @param librnName the librnName to set
	 */
	public void setLibrnName(String librnName) {
		this.librnName = librnName;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * @return the librnPassword
	 */
	public String getLibrnPassword() {
		return librnPassword;
	}

	/**
	 * @param librnPassword the librnPassword to set
	 */
	public void setLibrnPassword(String librnPassword) {
		this.librnPassword = librnPassword;
	}

	/**
	 * @return the librnYear
	 */
	public String getLibrnYear() {
		return librnYear;
	}

	/**
	 * @param librnYear the librnYear to set
	 */
	public void setLibrnYear(String librnYear) {
		this.librnYear = librnYear;
	}

	/**
	 * @return the librnEmail
	 */
	public String getLibrnEmail() {
		return librnEmail;
	}

	/**
	 * @param librnEmail the librnEmail to set
	 */
	public void setLibrnEmail(String librnEmail) {
		this.librnEmail = librnEmail;
	}	
   
}
