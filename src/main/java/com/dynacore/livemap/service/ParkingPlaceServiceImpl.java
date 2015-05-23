package com.dynacore.livemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dynacore.livemap.entity.hibernate.ParkingLogData;
import com.dynacore.livemap.entity.jsonrepresentations.FeatureCollection;
import com.dynacore.livemap.repository.ParkingPlaceRepository;


@Service("parkingPlaceService")
public class ParkingPlaceServiceImpl implements ParkingPlaceService {
	
	@Autowired 
	private ParkingPlaceRepository parkingPlaceRepository;
	
	@Override
	@Transactional
	public ParkingLogData save(ParkingLogData parkingPlace) {
		return parkingPlaceRepository.save(parkingPlace);
	}

	@Override
	@Transactional
	public void saveCollection(FeatureCollection fc) {

		for(int i=0; i < fc.getFeatures().size(); i++) {
			
				System.out.println(fc.getFeatures().get(i).getProperties().getName());
				System.out.println(fc.getFeatures().get(i).getProperties().getPubDate()); //XXX: omzetten naar unix ts
				System.out.println(fc.getFeatures().get(i).getProperties().getType()); 
				System.out.println(fc.getFeatures().get(i).getProperties().getState());
				System.out.println(fc.getFeatures().get(i).getProperties().getFreeSpaceLong());
				System.out.println(fc.getFeatures().get(i).getProperties().getFreeSpaceShort());
				System.out.println(fc.getFeatures().get(i).getProperties().getLongCapacity());
				System.out.println(fc.getFeatures().get(i).getProperties().getShortCapacity());
				
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
				
				save(property);				
			}
		}		
	}


