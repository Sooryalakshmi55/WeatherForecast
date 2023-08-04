package com.zerplabs.application.weatherforecast.service;

import java.util.List;

import com.zerplabs.application.weatherforecast.dto.WeatherForecastResponseDto;
import com.zerplabs.application.weatherforecast.model.LocationDetail;
import com.zerplabs.application.weatherforecast.model.WeatherForecastDetail;

public interface WeatherForecastService {
	public LocationDetail getWeatherForecastDetail(String city);
	

}
