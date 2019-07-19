package com.example.ISAums.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class RegisteredUser extends User{

	@OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AirlineTicket> tickets = new HashSet<AirlineTicket>();
	
	
	public RegisteredUser() {
		super();
	}
	public RegisteredUser(Long id, String firstName, String lastName, String email, String password, String city,
			String telephoneNum)
	{
		super(id, firstName, lastName, email, password, city,
			telephoneNum);
	}
	public Set<AirlineTicket> getTickets() {
		return tickets;
	}
	public void setTickets(Set<AirlineTicket> tickets) {
		this.tickets = tickets;
	}
}
