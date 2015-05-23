package com.dynacore.livemap.entity.hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="parkingplacesLog")
public class ParkingPlace {

	@Id
	@GeneratedValue
	private long idKey;
			   
	@Column(name="name")
	private String Id;	
	
	private String type;
	
	public long getIdKey() {
		return idKey;
	}

	public void setIdKey(long idKey) {
		this.idKey = idKey;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
