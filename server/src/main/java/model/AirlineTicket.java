package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AirlineTicket {

	@Column(name = "startPoint", nullable = false)
	private String startingPoint;

	@Column(name = "destPoint", nullable = false)
	private String destinationPoint;
	
	@Column(name = "date", nullable = false)
	private Date dateTime;
	
	@Column(name = "seat", nullable = false)
	private Integer airplaneSeat;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "discount", nullable = false)
	private Integer discount;
	
	public AirlineTicket() {}
	
	public AirlineTicket(String startingPoint, String destinationPoint, Date dateTime, Integer airplaneSeat,
			Double price, Integer discount) {
		super();
		this.startingPoint = startingPoint;
		this.destinationPoint = destinationPoint;
		this.dateTime = dateTime;
		this.airplaneSeat = airplaneSeat;
		this.price = price;
		this.discount = discount;
	}
	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public String getDestinationPoint() {
		return destinationPoint;
	}

	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getAirplaneSeat() {
		return airplaneSeat;
	}

	public void setAirplaneSeat(Integer airplaneSeat) {
		this.airplaneSeat = airplaneSeat;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}


	
	
	
	
}
