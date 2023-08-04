package com.zerplabs.application.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentWeatherDataDto {
	@JsonProperty(value="city_name")
	private String cityName;
	private double lat;
	private double lon;
	private int uv;
	private int clouds;
	private int aqi;
	
	@JsonProperty(value="ts")
	private Long observationDateTime;
	
	@JsonProperty(value = "wind_spd")
	private double windSpeed;
	
	@JsonProperty(value = "wind_cdir")
	private String windDirection;
	
	@JsonProperty(value = "rh")
	private int relativeHumidity;
		
	@JsonProperty(value = "sunrise")
	private String sunrise;
	
	@JsonProperty(value = "sunset")
	private String sunset;
	
	@JsonProperty(value = "temp")
	private double temp;
	
	private WeatherDescriptionDto weather;
	
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
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Long getObservationDateTime() {
		return observationDateTime;
	}
	public void setObservationDateTime(Long observationDateTime) {
		this.observationDateTime = observationDateTime;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public int getRelativeHumidity() {
		return relativeHumidity;
	}
	public void setRelativeHumidity(int relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	public String getSunrise() {
		return sunrise;
	}
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	public String getSunset() {
		return sunset;
	}
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public int getUv() {
		return uv;
	}
	public void setUv(int uv) {
		this.uv = uv;
	}
	public int getClouds() {
		return clouds;
	}
	public void setClouds(int clouds) {
		this.clouds = clouds;
	}
	public int getAqi() {
		return aqi;
	}
	public void setAqi(int aqi) {
		this.aqi = aqi;
	}
	public WeatherDescriptionDto getWeather() {
		return weather;
	}
	public void setWeather(WeatherDescriptionDto weather) {
		this.weather = weather;
	}
	
}


