package com.zerplabs.application.weatherforecast.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherForecastResponseDataDto {
	@JsonProperty(value = "valid_date")
	private String date;
	private int clouds;
	
	@JsonProperty(value = "wind_spd")
	private double windSpeed;
	
	@JsonProperty(value = "wind_cdir")
	private String windDirection;
	
	@JsonProperty(value = "rh")
	private int relativeHumidity;
		
	@JsonProperty(value = "sunrise_ts")
	private Long sunrise;
	
	@JsonProperty(value = "sunset_ts")
	private Long sunset;
	
	@JsonProperty(value = "min_temp")
	private double minTemp;
	
	@JsonProperty(value = "max_temp")
	private double maxTemp;
	
	private double temp;
	
	private int pop;
	
	private int precip;
	
	private WeatherDescriptionDto weather;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getClouds() {
		return clouds;
	}

	public void setClouds(int clouds) {
		this.clouds = clouds;
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

	public Long getSunrise() {
		return sunrise;
	}

	public void setSunrise(Long sunrise) {
		this.sunrise = sunrise;
	}

	public Long getSunset() {
		return sunset;
	}

	public void setSunset(Long sunset) {
		this.sunset = sunset;
	}

	public double getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}

	public double getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}

	public WeatherDescriptionDto getWeather() {
		return weather;
	}

	public void setWeather(WeatherDescriptionDto weather) {
		this.weather = weather;
	}

	public int getPop() {
		return pop;
	}

	public void setPop(int pop) {
		this.pop = pop;
	}

	public int getPrecip() {
		return precip;
	}

	public void setPrecip(int precip) {
		this.precip = precip;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

}
