package com.dynacore.testing123.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dynacore.testing123.entity.TriggerCamera;

@Controller
public class TrafficJamController {

	TriggerCamera triggerCamera = new TriggerCamera(false);
	boolean simulation = true;
	
	
	@RequestMapping("/triggercamera")	
	public @ResponseBody TriggerCamera getTrafficJamStatus() {
				
		return triggerCamera;
	}
	
	
	
	//This function can used for testing two way databinding..
	//example: http://localhost:777/mymvc/method9?id=1
	@RequestMapping(value="/setStatus")
	@ResponseBody
	public String setTriggerCamera(@RequestParam("status") boolean status){
		triggerCamera.setTrafficJam(status);
		
		return "TrafficJam Status is now " + triggerCamera.isTrafficJam();
	}
	
	
	
}
