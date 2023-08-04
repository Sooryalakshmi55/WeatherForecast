package com.zerplabs.application.weatherforecast.exceptions;

public class WeatherAPIConnectionError extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WeatherAPIConnectionError(String msg)
    {
        super(msg);
    }
}
