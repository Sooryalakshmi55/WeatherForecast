package com.zerplabs.application.weatherforecast.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zerplabs.application.weatherforecast.model.WeatherForecastDetail;

@Repository
public interface WeatherForecastDetailRepository extends JpaRepository<WeatherForecastDetail, Integer>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM WeatherForecastDetail WHERE location.id = :locationId")
	void deleteByLocationId(int locationId);

}
