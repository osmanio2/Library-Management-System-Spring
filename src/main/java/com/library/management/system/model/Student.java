package com.library.management.system.model;

import javax.persistence.*;

import com.library.management.system.type.UserType;
	
	@Entity
	@Table(name = "student_member")
	public class Student {
	
	@Id 
	@Column(name = "STUDENT_ID")
	@GeneratedValue
	 private int id;
	
	@Column(name = "UNIVERSITY_NUMBER")
	private String uniNum;
	
	@Column(name = "STUD_NAME")
	private String studName;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "USER_TYPE")
	private UserType userType;
			
    @Column(name = "STUD_PASSWORD")
    private String studPassword;
	
    @Column(name = "STUDY_YEAR")
    private String studYear;
    
    @Column(name = "STUD_EMAIL")
    private String studEmail;	
    
    @Column(name = "STUD_DEPARTMENT")
     private String studDepartment;

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
	 * @return the uniNum
	 */
	public String getUniNum() {
		return uniNum;
	}

	/**
	 * @param uniNum the uniNum to set
	 */
	public void setUniNum(String uniNum) {
		this.uniNum = uniNum;
	}

	/**
	 * @return the studName
	 */
	public String getStudName() {
		return studName;
	}

	/**
	 * @param studName the studName to set
	 */
	public void setStudName(String studName) {
		this.studName = studName;
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
	 * @return the studPassword
	 */
	public String getStudPassword() {
		return studPassword;
	}

	/**
	 * @param studPassword the studPassword to set
	 */
	public void setStudPassword(String studPassword) {
		this.studPassword = studPassword;
	}

	/**
	 * @return the studYear
	 */
	public String getStudYear() {
		return studYear;
	}

	/**
	 * @param studYear the studYear to set
	 */
	public void setStudYear(String studYear) {
		this.studYear = studYear;
	}

	/**
	 * @return the studEmail
	 */
	public String getStudEmail() {
		return studEmail;
	}

	/**
	 * @param studEmail the studEmail to set
	 */
	public void setStudEmail(String studEmail) {
		this.studEmail = studEmail;
	}

	/**
	 * @return the studDepartment
	 */
	public String getStudDepartment() {
		return studDepartment;
	}

	/**
	 * @param studDepartment the studDepartment to set
	 */
	public void setStudDepartment(String studDepartment) {
		this.studDepartment = studDepartment;
	}

}
