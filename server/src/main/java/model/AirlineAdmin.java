package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class AirlineAdmin extends User{

	@OneToMany(mappedBy = "airlineAdmin")
	private Set<String> destinations = new HashSet<String>();
	
	public AirlineAdmin() {
		super();
	}
	
	public AirlineAdmin(Long id, String firstName, String lastName, String email, String password, String city,
			String telephoneNum){
		super(id, firstName, lastName, email, password, city,
			telephoneNum);
	}

	public Set<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<String> destinations) {
		this.destinations = destinations;
	}
	
}
