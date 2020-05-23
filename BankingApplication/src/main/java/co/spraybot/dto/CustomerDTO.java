package co.spraybot.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import co.spraybot.model.Account;
import co.spraybot.model.BankLocation;
import co.spraybot.validation.PasswordMatches;
import co.spraybot.validation.ValidEmail;
import co.spraybot.validation.ValidPassword;

@PasswordMatches
public class CustomerDTO {
	// DTO = Data Transfer Object 
	
	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String gender;
	@NotEmpty
	private String DOB;
	
	@NotEmpty
	@ValidEmail
	private String email;
	
	@ValidPassword
	private String password;
	
	@NotEmpty
	private String matchingPassword;
	
	@NotEmpty
	private String mobile;
	
	@NotEmpty
	private String nationality;
	
	@NotNull
	private int salary;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		return super.equals(obj);
//	}
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", gender=" + gender +  ", DOB=" + DOB + ", email=" + email + ", mobile="
				+ mobile + ", nationality=" + nationality + ", salary=" + salary + "]";
		
	}
	
}
