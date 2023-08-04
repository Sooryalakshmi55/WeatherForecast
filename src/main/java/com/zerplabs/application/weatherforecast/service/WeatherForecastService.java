package com.zerplabs.application.weatherforecast.service;

import com.zerplabs.application.weatherforecast.model.LocationDetail;

public interface WeatherForecastService {
	public LocationDetail getWeatherForecastDetail(String city);
	

}
