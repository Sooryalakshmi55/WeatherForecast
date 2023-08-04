package com.zerplabs.application.weatherforecast.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="location_detail")
public class LocationDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="latitude")
	private double latitude;
	
	@Column(name="longitude")
	private double longitude;
	
	@Column(name="city")
	private String city;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "location", fetch = FetchType.LAZY)
	private CurrentWeatherDetail currentWeatherDetail;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location", fetch = FetchType.LAZY)
	private List<WeatherForecastDetail> weatherForecastDetail;
	
	public List<WeatherForecastDetail> getWeatherForecastDetail() {
		return weatherForecastDetail;
	}

	public void setWeatherForecastDetail(List<WeatherForecastDetail> weatherForecastDetail) {
		this.weatherForecastDetail = weatherForecastDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
