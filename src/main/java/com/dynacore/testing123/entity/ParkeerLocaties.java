package com.dynacore.testing123.entity;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkeerLocaties {
	public List<ParkeerLocatieTop> parkeerlocaties;

	public List<ParkeerLocatieTop> getParkeerLocaties() {
		return parkeerlocaties;
	}

	public void setParkeerLocaties(List<ParkeerLocatieTop> parkeerlocaties) {
		this.parkeerlocaties = parkeerlocaties;
	}

}
