package com.MiniProject.CourseManager.Entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

@Document(collection  = "SignUpDetails")
public class SignUpDetails {
    
	@Id
	private ObjectId id;
	
	@NonNull
	@Indexed(unique = true)
	private String email;
	
	private String firstName;
	
	private String lastName; 
	
	private String gender ; 
	
	private long phoneNumber;
	
	@DBRef
	private List<User> userDetailsLinkedToMail = new ArrayList<>(); 

	public ObjectId getId() {
		return id;
	}

	public List<User> getUserDetailsLinkedToMail() {
		return userDetailsLinkedToMail;
	}

	public void setUserDetailsLinkedToMail(List<User> userDetailsLinkedToMail) {
		this.userDetailsLinkedToMail = userDetailsLinkedToMail;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
