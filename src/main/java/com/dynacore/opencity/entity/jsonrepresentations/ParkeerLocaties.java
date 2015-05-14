package com.dynacore.opencity.entity.jsonrepresentations;

import java.util.List;

public class ParkeerLocaties {
	public List<ParkeerLocatieTop> parkeerlocaties;

	public List<ParkeerLocatieTop> getParkeerLocaties() {
		return parkeerlocaties;
	}

	public void setParkeerLocaties(List<ParkeerLocatieTop> parkeerlocaties) {
		this.parkeerlocaties = parkeerlocaties;
	}

}
