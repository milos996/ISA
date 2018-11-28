package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class RegisteredUser extends User{

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "pass", nullable = false)
	private String password;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "telephone", nullable = false)
	private String telephoneNum;
	
	@OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AirlineTicket> tickets = new HashSet<AirlineTicket>();
	
	
	public RegisteredUser() {
		super();
	}

	public RegisteredUser(String email, String password, String city, String telephoneNum, String fName, String lName) {
		super(fName, lName);
		this.email = email;
		this.password = password;
		this.city = city;
		this.telephoneNum = telephoneNum;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}

	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}

	public Set<AirlineTicket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<AirlineTicket> tickets) {
		this.tickets = tickets;
	}
	
	
}
