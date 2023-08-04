package com.zerplabs.application.weatherforecast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zerplabs.application.weatherforecast.model.LocationDetail;

@Repository
public interface LocationDetailRepository extends JpaRepository<LocationDetail, Integer>{
	
	@Query("select e from LocationDetail e where e.city= ?1")
	LocationDetail findByCity(String city);

}
