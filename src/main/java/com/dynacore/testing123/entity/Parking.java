package com.dynacore.testing123.entity;

import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
public class Parking {
	
	private String name;
	private String gps;
	
	public Parking(String name, String gps) {
		super();
		this.name = name;
		this.gps = gps;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
}
