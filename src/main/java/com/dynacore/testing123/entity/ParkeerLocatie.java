package com.dynacore.testing123.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ParkeerLocatie {
	
	private String opmerkingen;
	private String postcode;
	private String url;
	private String title;

//	public Locatie Locatie;
	
//	public Locatie getLocatie() {
//		return Locatie;
//	}
//	public void setLocatie(Locatie locatie) {
//		Locatie = locatie;
//	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOpmerkingen() {
		return opmerkingen;
	}
	public void setOpmerkingen(String opmerkingen) {
		this.opmerkingen = opmerkingen;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
