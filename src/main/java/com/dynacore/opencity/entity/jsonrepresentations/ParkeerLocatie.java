package com.dynacore.opencity.entity.jsonrepresentations;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


public class ParkeerLocatie {
	
	private String title;
	private String type;
	
	private String url;
	private String urltitle;
	private String adres;
	private String postcode;
	private String woonplaats;
	private String opmerkingen;
	
	public String Locatie; //Moet public ? :S
	public String OV_bus;
	public String OV_tram;
	public String OV;
	public String OV_metro;
	public String OV_trein;

	public String aantal;
	
	public String getAantal() {
		return aantal;
	}
	public void setAantal(String aantal) {
		this.aantal = aantal;
	}
	public String getOV_trein() {
		return OV_trein;
	}
	public void setOV_trein(String oV_trein) {
		OV_trein = oV_trein;
	}
	public String getOV_metro() {
		return OV_metro;
	}
	public void setOV_metro(String oV_metro) {
		OV_metro = oV_metro;
	}
	public String getOV_bus() {
		return OV_bus;
	}
	public void setOV_bus(String oV_bus) {
		OV_bus = oV_bus;
	}
	public String getOV_tram() {
		return OV_tram;
	}
	public void setOV_tram(String oV_tram) {
		OV_tram = oV_tram;
	}
	public String getOV() {
		return OV;
	}
	public void setOV(String oV) {
		OV = oV;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrltitle() {
		return urltitle;
	}
	public void setUrltitle(String urltitle) {
		this.urltitle = urltitle;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getWoonplaats() {
		return woonplaats;
	}
	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}
	public String getLocatie() {
		return Locatie;
	}
	public void setLocatie(String Locatie) {
		this.Locatie = Locatie;
	}	
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
