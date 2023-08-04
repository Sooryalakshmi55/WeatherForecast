package com.zerplabs.application.weatherforecast.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
	
//	@Column(name="created_date")
//	private Date createdDate;
//	
//	@Column(name="last_modified_date")
//	private Date lastModifiedDate;
	
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

//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public Date getLastModifiedDate() {
//		return lastModifiedDate;
//	}
//
//	public void setLastModifiedDate(Date lastModifiedDate) {
//		this.lastModifiedDate = lastModifiedDate;
//	}
	
	
	

}
