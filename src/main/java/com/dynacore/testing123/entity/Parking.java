package com.dynacore.testing123.entity;

import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
public class Parking {
	
	private int capacity;
	private int availableSpots;
	
	public Parking(int capacity) {
		this.capacity = capacity;
		availableSpots = -1;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getAvailableSpots() {
		return availableSpots;
	}
	
	public void setAvailableSpots(int availableSpots) {
		this.availableSpots = availableSpots;
	}
}
