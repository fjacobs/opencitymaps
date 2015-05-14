package com.dynacore.testing123.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dynacore.testing123.entity.Parking;

@SuppressWarnings("unused")
@Controller
public class ParkingController {

	private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);
	
	@RequestMapping(value="/x1")
	public List<Parking> getJsonParkingList(){
		
		Parking parking1 = new Parking(300);
		Parking parking2 = new Parking(235);
		Parking parking3 = new Parking(500);
		
		List<Parking> parkingList = new ArrayList<Parking>();
		parkingList.add(parking1);
		parkingList.add(parking2);
		parkingList.add(parking3);
			
		return parkingList;
	}
	
}
