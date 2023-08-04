package com.zerplabs.application.weatherforecast.serviceImpl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.zerplabs.application.weatherforecast.constants.WeatherBitAPIConstants;
import com.zerplabs.application.weatherforecast.dto.WeatherForecastResponseDataDto;
import com.zerplabs.application.weatherforecast.dto.WeatherForecastResponseDto;
import com.zerplabs.application.weatherforecast.exceptions.InvalidCityException;
import com.zerplabs.application.weatherforecast.exceptions.WeatherAPIConnectionError;
import com.zerplabs.application.weatherforecast.model.LocationDetail;
import com.zerplabs.application.weatherforecast.repository.WeatherForecastDetailRepository;
import com.zerplabs.application.weatherforecast.service.WeatherForecastService;
import com.zerplabs.application.weatherforecast.model.WeatherForecastDetail;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService{
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private UtilityService utilService;
	
	@Autowired
	private WeatherForecastDetailRepository weatherForecastRepo;

	@Override
	public LocationDetail getWeatherForecastDetail(String city) {
		
		Optional<LocationDetail> locationDetail = utilService.checkIfCityExists(city);
		
		// Checks if location already exists in location table
		if(locationDetail.isPresent())
		{
			System.out.println("City is present in Location table");
			LocationDetail location = locationDetail.get();
			List<WeatherForecastDetail> weatherForecastList = location.getWeatherForecastDetail();
			// checks if location contains weather forecast detail 
			if (weatherForecastList.size() == 0) {
				System.out.println("Forecast Data not present");
				return saveForecastDetails(city, location);
			}
			// checks if weather forecast detail which is already present for the location matches the current date
			else if(weatherForecastList.get(0).getDate().toString().equals(utilService.getCurrentdate().toString()))
			{
				System.out.println("Forecast Data already present");
				return location;
			}
			// delete the past weather forecast detail and save the updated weather forecast detail got from WeatherBit api
			else
			{
				System.out.println("Forecast Data present but it is fetched on past date");
				weatherForecastRepo.deleteByLocationId(location.getId());
				return saveForecastDetails(city, location);
				
			}
			
		}
		// Get the weather forecast detail if not exists in database
		else
		{
			System.out.println("City not present");
			return saveForecastDetails(city,null);
		}
		
		
	}
	
	private LocationDetail saveForecastDetails(String city, LocationDetail loc) {
		final String url = WeatherBitAPIConstants.BASE_URL + "/forecast/daily?city=" + city + "&key=" + WeatherBitAPIConstants.API_KEY;
		
		ResponseEntity<WeatherForecastResponseDto> weatherAPIResponse;
		try {
			// external api call to get weather forecast information
			weatherAPIResponse = webClientBuilder.build()
					 .get()
					 .uri(url)
					 .retrieve()
					 .toEntity(WeatherForecastResponseDto.class)
					 .block();
		}
		catch (Exception e) {
			String msg = "Error in calling WeatherBit Forecast API";
			System.out.println(msg);
			throw new WeatherAPIConnectionError(msg);
		}
		
		HttpStatusCode responseStatusCode = weatherAPIResponse.getStatusCode();
		System.out.println("Response Status Code : " + responseStatusCode);
		
		if (responseStatusCode == HttpStatus.NO_CONTENT) {
			throw new InvalidCityException("City is invalid");
		}
		WeatherForecastResponseDto result = weatherAPIResponse.getBody();
		
		// save the location if not already exists in location table
		if (loc == null)
			loc = utilService.saveLocation(result.getLat(), result.getLon(), result.getCityName());
		
		List<WeatherForecastResponseDataDto>  weatherForecastDataList  = result.getData();
		
		List<WeatherForecastDetail> weatherForecastToSave = new LinkedList<WeatherForecastDetail>();
		for(WeatherForecastResponseDataDto weatherForecastData: weatherForecastDataList)
		{
			WeatherForecastDetail forecastData = new WeatherForecastDetail();
			forecastData.setLocation(loc);
			forecastData.setDate(utilService.convertDate(weatherForecastData.getDate()));
			forecastData.setMintemp(weatherForecastData.getMinTemp());
			forecastData.setMaxtemp(weatherForecastData.getMaxTemp());
			forecastData.setTemperature(weatherForecastData.getTemp());
			forecastData.setClouds(weatherForecastData.getClouds());
			forecastData.setWindSpeed(weatherForecastData.getWindSpeed());
			forecastData.setWindDirection(weatherForecastData.getWindDirection());
			forecastData.setRelativeHumidity(weatherForecastData.getRelativeHumidity());
			forecastData.setSunrise(utilService.convertUnixTimestampToDate(weatherForecastData.getSunrise()));
			forecastData.setSunset(utilService.convertUnixTimestampToDate(weatherForecastData.getSunset()));
			forecastData.setDescription(weatherForecastData.getWeather().getDescription());
			forecastData.setProbabilityOfPrecipitation(weatherForecastData.getPop());
			forecastData.setPrecipitation(weatherForecastData.getPrecip());
			weatherForecastToSave.add(forecastData);
		}
		
		List<WeatherForecastDetail> savedWeatherForecastDeatil = weatherForecastRepo.saveAll(weatherForecastToSave);
		loc.setWeatherForecastDetail(savedWeatherForecastDeatil);
		
		return loc;
	}
}
