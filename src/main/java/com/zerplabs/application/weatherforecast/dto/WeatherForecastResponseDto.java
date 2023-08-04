package com.zerplabs.application.weatherforecast.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class WeatherForecastResponseDto {
	@JsonProperty(value="city_name")
	private String cityName;
	private double lat;
	private double lon;
	private List<WeatherForecastResponseDataDto> data;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<WeatherForecastResponseDataDto> getData() {
		return data;
	}
	public void setData(List<WeatherForecastResponseDataDto> data) {
		this.data = data;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
}
