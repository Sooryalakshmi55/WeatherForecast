package com.zerplabs.application.weatherforecast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zerplabs.application.weatherforecast.model.CurrentWeatherDetail;

@Repository
public interface CurrentWeatherDetailRepository extends JpaRepository<CurrentWeatherDetail, Integer>{
	
	@Query("select e from CurrentWeatherDetail e where e.location.id=?1")
	CurrentWeatherDetail findByLocationId(int locationId);

}
