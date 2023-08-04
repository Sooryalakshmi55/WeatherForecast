package com.zerplabs.application.weatherforecast.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="weather_forecast_detail")
public class WeatherForecastDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "location_id")
	private LocationDetail location;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="min_temperature")
	private double mintemp;
	
	@Column(name="max_temperature")
	private double maxtemp;
	
	@Column(name="temperature")
	private double temperature;
	
	@Column(name="clouds")
	private int clouds;
	
	@Column(name="wind_speed")
	private double windSpeed;
	
	@Column(name="wind_direction")
	private String windDirection;
	
	@Column(name="relative_humidity")
	private int relativeHumidity;
	
	@Column(name="sunrise")
	private Date sunrise;
	
	@Column(name="sunset")
	private Date sunset;
	
	@Column(name="description")
	private String description;
	
	@Column(name="probability_of_precipitation")
	private int probabilityOfPrecipitation;
	
	@Column(name="precipitation")
	private int precipitation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public double getMintemp() {
		return mintemp;
	}

	public void setMintemp(double mintemp) {
		this.mintemp = mintemp;
	}

	public double getMaxtemp() {
		return maxtemp;
	}

	public void setMaxtemp(double maxtemp) {
		this.maxtemp = maxtemp;
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

	public Date getSunrise() {
		return sunrise;
	}

	public void setSunrise(Date sunrise) {
		this.sunrise = sunrise;
	}

	public Date getSunset() {
		return sunset;
	}

	public void setSunset(Date sunset) {
		this.sunset = sunset;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProbabilityOfPrecipitation() {
		return probabilityOfPrecipitation;
	}

	public void setProbabilityOfPrecipitation(int probabilityOfPrecipitation) {
		this.probabilityOfPrecipitation = probabilityOfPrecipitation;
	}

	public int getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(int precipitation) {
		this.precipitation = precipitation;
	}
	
	

}
