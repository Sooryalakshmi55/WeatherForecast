package com.zerplabs.application.weatherforecast.dto;

import java.util.List;


public class CurrentWeatherDto {
	
	
	private List<CurrentWeatherDataDto> data;

	public List<CurrentWeatherDataDto> getData() {
		return data;
	}

	public void setData(List<CurrentWeatherDataDto> data) {
		this.data = data;
	}
}
