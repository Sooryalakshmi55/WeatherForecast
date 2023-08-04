package com.zerplabs.application.weatherforecast.model;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="current_weather_detail")
public class CurrentWeatherDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
    @JoinColumn(name = "location_id")
	@JsonIgnore
	private LocationDetail location;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="temperature")
	private double temperature;
	
	@Column(name="clouds")
	private int clouds;
	
	@Column(name="air_quality_index")
	private int airQualityIndex;
	
	@Column(name="wind_speed")
	private double windSpeed;
	
	@Column(name="wind_direction")
	private String windDirection;
	
	@Column(name="relative_humidity")
	private int relativeHumidity;
	
	@Column(name="uv_index")
	private int uvIndex;
	
	@Column(name="sunrise")
	private Time sunrise;
	
	@Column(name="sunset")
	private Time sunset;
	
	@Column(name="description")
	private String description;

	public LocationDetail getLocation() {
		return location;
	}

	public void setLocation(LocationDetail location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getClouds() {
		return clouds;
	}

	public void setClouds(int clouds) {
		this.clouds = clouds;
	}

	public int getAirQualityIndex() {
		return airQualityIndex;
	}

	public void setAirQualityIndex(int airQualityIndex) {
		this.airQualityIndex = airQualityIndex;
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

	public int getUvIndex() {
		return uvIndex;
	}

	public void setUvIndex(int uvIndex) {
		this.uvIndex = uvIndex;
	}

	public Time getSunrise() {
		return sunrise;
	}

	public void setSunrise(Time sunrise) {
		this.sunrise = sunrise;
	}

	public Time getSunset() {
		return sunset;
	}

	public void setSunset(Time sunset) {
		this.sunset = sunset;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

}
