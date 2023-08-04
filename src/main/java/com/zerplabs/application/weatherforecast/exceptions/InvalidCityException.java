package com.zerplabs.application.weatherforecast.exceptions;

public class InvalidCityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public InvalidCityException(String msg)
    {
        super(msg);
    }
}
