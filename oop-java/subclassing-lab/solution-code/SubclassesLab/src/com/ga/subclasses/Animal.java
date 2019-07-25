package com.ga.subclasses;

public class Animal {
	
	
	private float topSpeed;
	private Boolean isEndangered;
	private String name;
	
	public Animal(float topSpeed, Boolean isEndangered, String name) {
		this.topSpeed = topSpeed;
		this.isEndangered = isEndangered;
		this.name = name;
	}

	public float getTopSpeed() {
		return topSpeed;
	}

	public Boolean getIsEndangered() {
		return isEndangered;
	}

	public String getName() {
		return name;
	}
	
	
}
