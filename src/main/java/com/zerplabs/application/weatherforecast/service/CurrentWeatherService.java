package com.zerplabs.application.weatherforecast.service;

import com.zerplabs.application.weatherforecast.model.CurrentWeatherDetail;

public interface CurrentWeatherService {
	public CurrentWeatherDetail getCurrentWeatherDetail(String city);
}
