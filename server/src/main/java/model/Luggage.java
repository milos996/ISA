package model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Luggage {

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "weight", nullable = false)
	private Double weight;
	
	@Column(name = "dimensions", nullable = false)
	private Integer[] dimensions = new Integer[3];

	public Luggage(Integer quantity, Double weight, Integer[] dimensions) {
		super();
		this.quantity = quantity;
		this.weight = weight;
		this.dimensions = dimensions;
	}

	public Luggage() {}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer[] getDimensions() {
		return dimensions;
	}

	public void setDimensions(Integer[] dimensions) {
		this.dimensions = dimensions;
	}
	
	
}
