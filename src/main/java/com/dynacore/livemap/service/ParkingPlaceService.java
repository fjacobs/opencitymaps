package com.dynacore.livemap.service;

import com.dynacore.livemap.entity.hibernate.ParkingLogData;
import com.dynacore.livemap.entity.jsonrepresentations.FeatureCollection;

public interface ParkingPlaceService {

	void saveCollection(FeatureCollection fc);

	ParkingLogData save(ParkingLogData parkingPlace);
}


