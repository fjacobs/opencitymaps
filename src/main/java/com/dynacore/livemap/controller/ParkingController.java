package com.dynacore.livemap.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dynacore.livemap.service.ParkingPlaceService;
import com.dynacore.testing123.entity.Parking;

@SuppressWarnings("unused")
@Controller
public class ParkingController {


	
	private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);
	
	@RequestMapping(value="/x1")
	public List<Parking> getJsonParkingList(){
			
		List<Parking> parkingList = new ArrayList<Parking>();

		return parkingList;
	}
	
}
