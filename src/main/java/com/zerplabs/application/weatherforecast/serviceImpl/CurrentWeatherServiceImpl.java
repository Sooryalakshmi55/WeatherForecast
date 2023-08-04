package com.zerplabs.application.weatherforecast.serviceImpl;

import java.sql.Time;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zerplabs.application.weatherforecast.constants.WeatherBitAPIConstants;
import com.zerplabs.application.weatherforecast.dto.CurrentWeatherResponseDataDto;
import com.zerplabs.application.weatherforecast.dto.CurrentWeatherResponseDto;
import com.zerplabs.application.weatherforecast.exceptions.InvalidCityException;
import com.zerplabs.application.weatherforecast.exceptions.WeatherAPIConnectionError;
import com.zerplabs.application.weatherforecast.model.LocationDetail;
import com.zerplabs.application.weatherforecast.repository.CurrentWeatherDetailRepository;
import com.zerplabs.application.weatherforecast.repository.LocationDetailRepository;
import com.zerplabs.application.weatherforecast.service.CurrentWeatherService;
import com.zerplabs.application.weatherforecast.model.CurrentWeatherDetail;


//import com.google.gson.Gson;

@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherService{
	
//	@Autowired
//	private RestTemplate template;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private LocationDetailRepository locationRepo;
	
	@Autowired
	private CurrentWeatherDetailRepository currentWeatherRepo;
	
	@Autowired
	private UtilityService utilService;
	
	@Override
	public CurrentWeatherDetail getCurrentWeatherDetail(String city) {
		
		Optional<LocationDetail> locationDetail = utilService.checkIfCityExists(city);
		
		//Checks if location already exist in location table
		if(locationDetail.isPresent())
		{
			LocationDetail location = locationDetail.get();
			Optional<CurrentWeatherDetail> currentWeather = checkIfLocationExistsInCurrentWeather(location.getId());
			
			// checks if current weather already exists for location
			if(currentWeather.isPresent())
			{
				// If current weather for current date already exists in table, return the record
				if(currentWeather.get().getDate().toString().equals(utilService.getCurrentdate().toString()))
				{
							System.out.println("Inside If");
							return currentWeather.get();
				}
				
				// Otherwise update the record by calling the Weatherbit API and return the data
				else
				{
					CurrentWeatherDetail currentWeatherToUpdate = currentWeather.get();
					CurrentWeatherResponseDataDto responseData = getCurrentWeatherFromWeatherBit(city);
					
					java.sql.Date dateObj = new java.sql.Date(responseData.getObservationDateTime() * 1000);
					currentWeatherToUpdate.setDate(dateObj);
					currentWeatherToUpdate.setTemperature(responseData.getTemp());
					currentWeatherToUpdate.setClouds(responseData.getClouds());
					currentWeatherToUpdate.setAirQualityIndex(responseData.getAqi());
					currentWeatherToUpdate.setWindSpeed(responseData.getWindSpeed());
					currentWeatherToUpdate.setWindDirection(responseData.getWindDirection());
					currentWeatherToUpdate.setRelativeHumidity(responseData.getRelativeHumidity());
					currentWeatherToUpdate.setUvIndex(responseData.getUv());
					Time sunriseTime = utilService.getTime(responseData.getSunrise());
					currentWeatherToUpdate.setSunrise(sunriseTime);
					Time sunsetTime = utilService.getTime(responseData.getSunset());
					currentWeatherToUpdate.setSunset(sunsetTime);
					currentWeatherToUpdate.setDescription(responseData.getWeather().getDescription());
					
					CurrentWeatherDetail currentWeatherUpdated = currentWeatherRepo.save(currentWeatherToUpdate);
					return currentWeatherUpdated;
					
				}
				
				
			}
			// Get current weather detail by calling WeatherBit api if not exists in database
			else
			{
				CurrentWeatherResponseDataDto responseData = getCurrentWeatherFromWeatherBit(city);
				CurrentWeatherDetail currentWeatherResponse = saveCurrentWeatherDetail(location, responseData);
				return currentWeatherResponse;
				
			}
			
		}
		// this condition will execute if location and current weather is not already present in database
		else
		{
			// If city does not exist in location table, call Weatherbit API, store it in table and return the data
			return getCurrentWeather(city);
			
		}
		
	}
	
	
	private CurrentWeatherDetail getCurrentWeather(String city)
	{
		
		CurrentWeatherResponseDataDto responseData = getCurrentWeatherFromWeatherBit(city);
		
		LocationDetail locationData = utilService.saveLocation(responseData.getLat(),responseData.getLon(),responseData.getCityName());
		
		CurrentWeatherDetail currentWeatherResponse = saveCurrentWeatherDetail(locationData, responseData);
		
		return currentWeatherResponse;
	}
	
	private  CurrentWeatherResponseDataDto getCurrentWeatherFromWeatherBit(String city)
	{
		final String url = WeatherBitAPIConstants.BASE_URL + "/current?city=" + city + "&key=" + WeatherBitAPIConstants.API_KEY;
		
		ResponseEntity<CurrentWeatherResponseDto> weatherAPIResponse;
		try {
			weatherAPIResponse = webClientBuilder.build()
					 .get()
					 .uri(url)
					 .retrieve()
					 .toEntity(CurrentWeatherResponseDto.class)
					 .block();
		}
		catch (Exception e) {
			String msg = "Error in calling WeatherBit Current Weather API";
			System.out.println(msg);
			throw new WeatherAPIConnectionError(msg);
		}
				
		HttpStatusCode responseStatusCode = weatherAPIResponse.getStatusCode();
		System.out.println("Response Status Code : " + responseStatusCode);
		
		if (responseStatusCode == HttpStatus.NO_CONTENT) {
			throw new InvalidCityException("City is invalid");
		}

		CurrentWeatherResponseDto weatherResponse = weatherAPIResponse.getBody();
		CurrentWeatherResponseDataDto responseData = weatherResponse.getData().get(0);
		return responseData;
	}
	
	
	private CurrentWeatherDetail saveCurrentWeatherDetail(LocationDetail locationData, CurrentWeatherResponseDataDto responseData)
	{
		CurrentWeatherDetail currentWeatherDetail = new CurrentWeatherDetail();
		
		currentWeatherDetail.setLocation(locationData);
		
		java.sql.Date dateObj = new java.sql.Date(responseData.getObservationDateTime() * 1000);
		currentWeatherDetail.setDate(dateObj);
		currentWeatherDetail.setTemperature(responseData.getTemp());
		currentWeatherDetail.setClouds(responseData.getClouds());
		currentWeatherDetail.setAirQualityIndex(responseData.getAqi());
		currentWeatherDetail.setWindSpeed(responseData.getWindSpeed());
		currentWeatherDetail.setWindDirection(responseData.getWindDirection());
		currentWeatherDetail.setRelativeHumidity(responseData.getRelativeHumidity());
		currentWeatherDetail.setUvIndex(responseData.getUv());
		Time sunriseTime = utilService.getTime(responseData.getSunrise());
		currentWeatherDetail.setSunrise(sunriseTime);
		Time sunsetTime = utilService.getTime(responseData.getSunset());
		currentWeatherDetail.setSunset(sunsetTime);
		currentWeatherDetail.setDescription(responseData.getWeather().getDescription());
		
		CurrentWeatherDetail currentWeatherDetailResponse = currentWeatherRepo.save(currentWeatherDetail);
		
		return currentWeatherDetailResponse;
		
	}
	
	private Optional<CurrentWeatherDetail> checkIfLocationExistsInCurrentWeather(int locationId)
	{
		return Optional.ofNullable(currentWeatherRepo.findByLocationId(locationId));
	}
	
}
