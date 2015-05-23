package com.dynacore.livemap.entity.jsonrepresentations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class ParkingPlace {

	private long idKey;

	private String Id;

	private String type;
	
	private Geometry geometry;
	
	private Properties properties;

	public Geometry getGeometry() {
		return geometry;
	}

	public String getId() {
		return Id;
	}

	public long getIdKey() {
		return idKey;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getType() {
		return type;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setIdKey(long idKey) {
		this.idKey = idKey;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setType(String type) {
		this.type = type;
	}

}
