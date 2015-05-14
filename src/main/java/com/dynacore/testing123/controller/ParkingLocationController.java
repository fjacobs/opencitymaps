package com.dynacore.testing123.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dynacore.testing123.entity.Coordinates;
import com.dynacore.testing123.entity.ParkeerLocatie;
import com.dynacore.testing123.entity.ParkeerLocatieTop;
import com.dynacore.testing123.entity.ParkeerLocaties;

/*
 * type:
 * Fietspunt
 * P+R
 * Parkeergarage
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Controller
public class ParkingLocationController {

	@RequestMapping(value = "/parkinglocationcontroller")
	@ResponseBody
	public String getParkingLocations() {
		String result = "nr of parkings";

		RestTemplate restTemplate = new RestTemplate();
		System.out.println("printdebug 1");
		ParkeerLocaties topArray;
		try {
			topArray = restTemplate.getForObject("http://www.amsterdamopendata.nl/files/ivv/parkeren/locaties.json",
												 ParkeerLocaties.class);
			System.out.println(topArray.parkeerlocaties.size());
			result += topArray.parkeerlocaties.size();

			List<ParkeerLocatieTop> x = topArray.getParkeerLocaties();
			
			 for (int i = 0; i < x.size(); i++) {
			     String title = x.get(i).getParkeerlocatie().getTitle();
			     System.out.println("name:" + title);
			   //  List<Coordinates> coordinates = x.get(i).getParkeerlocatie().getLocatie().getCoordinates();

			 }							

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("printdebug 2");

		// // System.out.println("Name:    " + page.getName());
		// // System.out.println("About:   " + page.getAbout());
		// // System.out.println("Phone:   " + page.getPhone());
		// // System.out.println("Website: " + page.getWebsite());
		//
		return result;
	}

}
