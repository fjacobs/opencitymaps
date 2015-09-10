package com.dynacore.livemap.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.dynacore.livemap.entity.hibernate.ParkingLogData;
import com.dynacore.livemap.entity.jsonrepresentations.parking.FeatureCollection;
import com.dynacore.livemap.repository.ParkingPlaceRepository;


@Service("parkingPlaceService")
public class ParkingPlaceServiceImpl implements ParkingPlaceService {
	
	@Autowired 
	private ParkingPlaceRepository parkingPlaceRepository;
	private FeatureCollection unprocessedJson, processedJson;

	private String latestPubdate, currentPubdate;
	private int updateInterval = 60; 
	
	//Returns resttemplate that supports different mediatypes	
	private RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();			
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>(); 
		supportedMediaTypes.add(MediaType.ALL);			
		jacksonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);			
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>(); 
		messageConverters.add(jacksonMessageConverter);		
		restTemplate.getMessageConverters().add(jacksonMessageConverter);
		return restTemplate;
	}	

	public ParkingPlaceServiceImpl() {		
		latestPubdate = new String();
		currentPubdate = new String();
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		  
		  @Override		  
		  public void run() { 
		    System.out.println("ParkingPlaceService: " + LocalDateTime.now());
			RestTemplate restTemplate = createRestTemplate();
						try {								
					unprocessedJson = restTemplate.getForObject("http://www.trafficlink-online.nl/trafficlinkdata/wegdata/IDPA_ParkingLocation.GeoJSON", FeatureCollection.class);
					saveCollection(unprocessedJson);					
					customizeJson(unprocessedJson);
					processedJson = unprocessedJson;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		}, 0, updateInterval, TimeUnit.SECONDS);	
	}	
	
	//This method adds stuff to the inserted json (only percentage for now)
	private FeatureCollection customizeJson(FeatureCollection top) {
		try {
			
			//Calculate percentage (How full is the parking) and insert into new json format
			for(int i=0; i < top.getFeatures().size(); i++) {
		
				int capacity = Integer.parseInt(top.getFeatures().get(i).getProperties().getShortCapacity());
				int parkedCars = (capacity - Integer.parseInt(top.getFeatures().get(i).getProperties().getFreeSpaceShort()));
				int percentage = (int) ((float) (parkedCars *100) / capacity);
				
				top.getFeatures().get(i).getProperties().setPercentage(percentage);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return top;
	}
	
	public FeatureCollection getProcessedJson() {
		return processedJson;
	}
		
	@Override
	public ParkingLogData save(ParkingLogData parkingPlace) {
		return parkingPlaceRepository.save(parkingPlace);
	}

	@Override
	@Transactional
	public void saveCollection(FeatureCollection fc) {
		
		System.out.println("Saving featurecollection: " + (System.currentTimeMillis() / 1000L));
		
		for(int i=0; i < fc.getFeatures().size(); i++) {
		
				ParkingLogData property = new ParkingLogData(
						fc.getFeatures().get(i).getProperties().getName(),
						fc.getFeatures().get(i).getProperties().getPubDate(), 
						fc.getFeatures().get(i).getProperties().getType(), 
						fc.getFeatures().get(i).getProperties().getState(), 
						fc.getFeatures().get(i).getProperties().getFreeSpaceShort(), 
						fc.getFeatures().get(i).getProperties().getFreeSpaceLong(), 
						fc.getFeatures().get(i).getProperties().getShortCapacity(), 
						fc.getFeatures().get(i).getProperties().getLongCapacity()
				);
				
				//only store logdata at startup of the application or if it has changed.
				if( latestPubdate.isEmpty() ||  ! latestPubdate.equals( property.getPubDate() )) {				
					save(property);
					currentPubdate = property.getPubDate(); //TODO: Checken of currentPubdate voor latestpubdate viel.
				}
			}
		
			latestPubdate = currentPubdate;
		
		}		
	}


