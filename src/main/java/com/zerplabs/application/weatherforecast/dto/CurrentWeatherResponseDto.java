package com.zerplabs.application.weatherforecast.dto;

import java.util.List;


public class CurrentWeatherResponseDto {
	
	
	private List<CurrentWeatherResponseDataDto> data;

	public List<CurrentWeatherResponseDataDto> getData() {
		return data;
	}

	public void setData(List<CurrentWeatherResponseDataDto> data) {
		this.data = data;
	}
}
