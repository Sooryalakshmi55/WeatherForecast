package com.zerplabs.application.weatherforecast.serviceImpl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zerplabs.application.weatherforecast.model.LocationDetail;
import com.zerplabs.application.weatherforecast.repository.LocationDetailRepository;

@Service
public class UtilityService {
	
	@Autowired
	private LocationDetailRepository locationRepo;
	
	public java.sql.Date getCurrentdate() {
		long millis = System.currentTimeMillis();
		java.sql.Date currentDate = new java.sql.Date(millis);
        return currentDate;
	}
	
	public Time getTime(String timeStr)
	{
			DateFormat df = new SimpleDateFormat("HH:mm");
			try {
				long time = df.parse(timeStr).getTime();
				return new Time(time);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public Optional<LocationDetail> checkIfCityExists(String city)
	{
		return Optional.ofNullable(locationRepo.findByCity(city));
	}
	
	public LocationDetail saveLocation(double lat, double lon, String city)
	{
		LocationDetail location = new LocationDetail();
		location.setLatitude(lat);
		location.setLongitude(lon);
		location.setCity(city);
		LocationDetail locationDetail = locationRepo.save(location);
		return locationDetail;
	}
	
	public java.sql.Date convertDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date dt = sdf.parse(date);
			return new java.sql.Date(dt.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public java.sql.Date convertUnixTimestampToDate(long date) {
		return new java.sql.Date(date * 1000);
	}

}
