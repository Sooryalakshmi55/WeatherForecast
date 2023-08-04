package com.zerplabs.application.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zerplabs.application.weatherforecast.dto.ResponseUtil;
import com.zerplabs.application.weatherforecast.exceptions.InvalidCityException;
import com.zerplabs.application.weatherforecast.exceptions.WeatherAPIConnectionError;
import com.zerplabs.application.weatherforecast.model.CurrentWeatherDetail;
import com.zerplabs.application.weatherforecast.model.LocationDetail;
import com.zerplabs.application.weatherforecast.service.CurrentWeatherService;
import com.zerplabs.application.weatherforecast.service.WeatherForecastService;

import org.springframework.dao.DataAccessResourceFailureException;

@RestController
@RequestMapping("/api/weather")
public class WeatherDetailController {
		
		@Autowired
		private CurrentWeatherService weatherForecastDetailService;
		
		@Autowired
		private WeatherForecastService weatherForecastService;
		
		// To get current weather information
		@GetMapping("/current")
		public ResponseEntity<ResponseUtil> getCurrentWeatherDetail(@RequestParam(required = true) String city)
		{
			try {
				if (city == "") {
					ResponseUtil response = new ResponseUtil("fail", "Please enter city", null);
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
				CurrentWeatherDetail result = weatherForecastDetailService.getCurrentWeatherDetail(city);
				ResponseUtil response = new ResponseUtil("success","Current Weather Details", result);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			catch (InvalidCityException ce) {
				ResponseUtil response = new ResponseUtil("fail", ce.getMessage(), null);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			catch (WeatherAPIConnectionError we) {
				ResponseUtil response = new ResponseUtil("fail", we.getMessage(), null);
				return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
			}
			catch (DataAccessResourceFailureException de) {
				System.out.println("Exception : " + de.getMessage());
				ResponseUtil response = new ResponseUtil("fail", "Exception in connecting to DataBase", null);
				return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
			}
			catch (Exception e) {
				System.out.println("Exception : " + e.getClass().getName());
				ResponseUtil response = new ResponseUtil("fail", "Exception in retrieving current weather data", null);
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// To get weather forecast information for next 16 days
		@GetMapping("/forecast")
		public ResponseEntity<ResponseUtil> getWeatherForecastDetail(@RequestParam(required = true) String city)
		{
			try {
				if (city == "") {
					ResponseUtil response = new ResponseUtil("fail", "Please enter city", null);
					return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
				}
				LocationDetail result = weatherForecastService.getWeatherForecastDetail(city);
				ResponseUtil response = new ResponseUtil("success","Weather Forecast Details", result);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			catch (InvalidCityException ce) {
				ResponseUtil response = new ResponseUtil("fail", ce.getMessage(), null);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			catch (WeatherAPIConnectionError we) {
				ResponseUtil response = new ResponseUtil("fail", we.getMessage(), null);
				return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
			}
			catch (DataAccessResourceFailureException de) {
				System.out.println("Exception : " + de.getMessage());
				ResponseUtil response = new ResponseUtil("fail", "Exception in connecting to DataBase", null);
				return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
			}
			catch (Exception e) {
				System.out.println("Exception : " + e.getMessage());
				ResponseUtil response = new ResponseUtil("fail", "Exception in retrieving weather forecast data", null);
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

}
