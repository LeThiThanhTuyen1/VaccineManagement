package com.example.vaccineapp.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
public class Vaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String manufacturer;

	@Column(name = "expiration_date")
	private LocalDate expirationDate;

	@Column
	private Integer quantity;

	@Column
	private int price;

	@Enumerated(EnumType.STRING)
	@Column
	private Status status;

	@Column(columnDefinition = "TEXT")
	private String description;
	
	public enum Status {
        IN_STOCK("TỒN KHO"),
        EXPIRATION("HẾT");
    	
    	private final String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
	
	// Constructors, Getters, and Setters
	public Vaccine() {
	}

	public Vaccine(String name, String manufacturer, LocalDate expirationDate, Integer quantity, int price,
			Status status, String description) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.expirationDate = expirationDate;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}