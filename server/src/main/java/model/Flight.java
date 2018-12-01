package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Flight {

	@Column(name = "dateTakeOff", unique = true, nullable = false)
	private String dateTakeOff;
	
	@Column(name = "dateLanding", unique = true, nullable = false)
	private String dateLanding;
	
	@Column(name = "timeOfTravel", unique = true, nullable = false)
	private Double timeOfTravel;

	@Column(name = "lengthOfTravel", unique = true, nullable = false)
	private Double lengthOfTravel;
	
	@Column(name = "transfers", unique = true, nullable = false)
	private Integer numOfTransfer;
	
	@Column(name = "locations", unique = true, nullable = false)
	private Set<String> locationsOfTransfer = new HashSet<String>();
	
	@Column(name = "price", unique = true, nullable = false)
	private Double price;

	
	public Flight() {}
	


	public Flight(String dateTakeOff, String dateLanding, Double timeOfTravel, Double lengthOfTravel,
			Integer numOfTransfer, Set<String> locationsOfTransfer, Double price) {
		super();
		this.dateTakeOff = dateTakeOff;
		this.dateLanding = dateLanding;
		this.timeOfTravel = timeOfTravel;
		this.lengthOfTravel = lengthOfTravel;
		this.numOfTransfer = numOfTransfer;
		this.locationsOfTransfer = locationsOfTransfer;
		this.price = price;
	}



	public String getDateTakeOff() {
		return dateTakeOff;
	}

	public void setDateTakeOff(String dateTakeOff) {
		this.dateTakeOff = dateTakeOff;
	}

	public String getDateLanding() {
		return dateLanding;
	}

	public void setDateLanding(String dateLanding) {
		this.dateLanding = dateLanding;
	}

	public Double getTimeOfTravel() {
		return timeOfTravel;
	}

	public void setTimeOfTravel(Double timeOfTravel) {
		this.timeOfTravel = timeOfTravel;
	}

	public Double getLengthOfTravel() {
		return lengthOfTravel;
	}

	public void setLengthOfTravel(Double lengthOfTravel) {
		this.lengthOfTravel = lengthOfTravel;
	}

	public Integer getNumOfTransfer() {
		return numOfTransfer;
	}

	public void setNumOfTransfer(Integer numOfTransfer) {
		this.numOfTransfer = numOfTransfer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	
}
