package com.example.ISAums.dto;

import com.example.ISAums.model.User;

public class UserDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String telephoneNum;

	public UserDTO() {}
	
	public UserDTO(String firstName, String lastName, String email, String city, String telephoneNum) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.telephoneNum = telephoneNum;
	}

	public UserDTO(User user)
	{
		this(user.getFirstName(), user.getLastName(), user.getEmail(), user.getCity(), user.getTelephoneNum());
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getCity() {
		return city;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}
}
