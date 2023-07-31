package com.example.jspnew;

public class Car {

	private int id;
	private String make;
	private String model;

	private String enginecapacity;

	private String color;

	private String doors;

	private String suspension;

	private int price;

	private String description;

	public Car() {

	}

	public Car(String make, String model, String enginecapacity, String color, String doors, String suspension, int price, String description) {
		this.make = make;
		this.model = model;
		this.enginecapacity = enginecapacity;
		this.color = color;
		this.doors = doors;
		this.suspension = suspension;
		this.price = price;
		this.description = description;
	}

	public Car(int id, String make, String model, String enginecapacity, String color, String doors, String suspension, int price, String description) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.enginecapacity = enginecapacity;
		this.color = color;
		this.doors = doors;
		this.suspension = suspension;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEnginecapacity() {
		return enginecapacity;
	}

	public void setEnginecapacity(String enginecapacity) {
		this.enginecapacity = enginecapacity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDoors() {
		return doors;
	}

	public void setDoors(String doors) {
		this.doors = doors;
	}

	public String getSuspension() {
		return suspension;
	}

	public void setSuspension(String suspension) {
		this.suspension = suspension;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "car{" +
				"id=" + id +
				", make='" + make + '\'' +
				", model='" + model + '\'' +
				", enginecapacity='" + enginecapacity + '\'' +
				", color='" + color + '\'' +
				", doors='" + doors + '\'' +
				", suspension='" + suspension + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				'}';
	}
}
