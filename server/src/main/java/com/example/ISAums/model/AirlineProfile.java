package com.example.ISAums.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AirlineProfile {

	@Column(name = "companyName", unique = true, nullable = false)
	private String companyName;
	
	@Column(name = "adress", unique = true, nullable = false)
	private String adress;
	
	@Column(name = "description", unique = true, nullable = false)
	private String promoDescription;
	
	@OneToMany(mappedBy = "airlineCompany")
	private Set<String> destinations = new HashSet<String>();

	@OneToMany(mappedBy = "airlineCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Flight> flights = new HashSet<Flight>();
	
	//@OneToMany(mappedBy = "airlineCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private Set<AirlineTicket> fastTicketReservation = new HashSet<AirlineTicket>();
		
	@OneToMany(mappedBy = "airlineCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AirlinePricelist> pricelist = new HashSet<AirlinePricelist>();
	
	@OneToOne(mappedBy = "airlineCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Luggage infoLuggage;
	
//	Konfiguracija segmenata i mesta u avionima ?
	
	public AirlineProfile() {}

	public AirlineProfile(String companyName, String adress, String promoDescription, Luggage luggage) {
		super();
		this.companyName = companyName;
		this.adress = adress;
		this.promoDescription = promoDescription;
		this.infoLuggage = luggage;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPromoDescription() {
		return promoDescription;
	}

	public void setPromoDescription(String promoDescription) {
		this.promoDescription = promoDescription;
	}

	public Set<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<String> destinations) {
		this.destinations = destinations;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public Set<AirlinePricelist> getPricelist() {
		return pricelist;
	}

	public void setPricelist(Set<AirlinePricelist> pricelist) {
		this.pricelist = pricelist;
	}

	public Luggage getInfoLuggage() {
		return infoLuggage;
	}

	public void setInfoLuggage(Luggage infoLuggage) {
		this.infoLuggage = infoLuggage;
	}


}
