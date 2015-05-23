package com.dynacore.livemap.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.dynacore.livemap.entity.hibernate.ParkingLogData;

@Repository("parkingPlaceRepository")
public class ParkingPlaceRepositoryImpl implements ParkingPlaceRepository {

    @PersistenceContext
	private EntityManager em;
	    
	@Override
	public ParkingLogData save(ParkingLogData parkingPlace) {
		// TODO Auto-generated method stub
	    em.persist(parkingPlace);
	    em.flush();
		return parkingPlace;
	}
	
}

