package com.dynacore.testing123.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Locatie {
	String Type;
//	List<Coordinates> coordinates;
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
//	public List<Coordinates> getCoordinates() {
//		return coordinates;
//	}
//	public void setCoordinates(List<Coordinates> coordinates) {
//		this.coordinates = coordinates;
//	}
}
