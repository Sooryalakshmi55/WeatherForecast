# WeatherForecast

This application is used to get current weather data and forecast data for the next few days. Stored the data in MySQL DataBase.

## Requirements
For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)

## Third Party API Used
Used an external third party service named `Weatherbit` to fetch weather data.
- https://api.weatherbit.io/v2.0/current : To fetch current weather details
- https://api.weatherbit.io/v2.0/forecast/daily : To fetch weather forecast details for 16 days.

## APIs

There are 2 APIs developed.
- GET : /api/weather/current : To get current weather details
### API : http://localhost:8080/api/weather/current?city=Coimbatore
### Sample Response
```
{
    "status": "success",
    "message": "Current Weather Details",
    "data": {
        "date": "2023-08-04",
        "temperature": 25.0,
        "clouds": 0,
        "airQualityIndex": 37,
        "windSpeed": 4.1,
        "windDirection": "SSW",
        "relativeHumidity": 88,
        "uvIndex": 0,
        "sunrise": "00:40:00",
        "sunset": "13:15:00",
        "description": "Clear sky"
    }
}
```
- GET : /api/weather/forecast : To get weather forecast details
### API : http://localhost:8080/api/weather/forecast?city=Coimbatore
### Sample Response
```
{
    "status": "success",
    "message": "Weather Forecast Details",
    "data": {
        "id": 2,
        "latitude": 11.00555,
        "longitude": 76.96612,
        "city": "Coimbatore",
        "weatherForecastDetail": [
            {
                "id": 1,
                "date": "2023-08-05",
                "mintemp": 21.4,
                "maxtemp": 33.1,
                "temperature": 26.3,
                "clouds": 21,
                "windSpeed": 5.4,
                "windDirection": "SW",
                "relativeHumidity": 66,
                "sunrise": "2023-08-05",
                "sunset": "2023-08-05",
                "description": "Scattered clouds",
                "probabilityOfPrecipitation": 0,
                "precipitation": 0
            },
            {
                "id": 2,
                "date": "2023-08-06",
                "mintemp": 21.9,
                "maxtemp": 33.3,
                "temperature": 26.5,
                "clouds": 22,
                "windSpeed": 5.8,
                "windDirection": "SW",
                "relativeHumidity": 67,
                "sunrise": "2023-08-06",
                "sunset": "2023-08-06",
                "description": "Scattered clouds",
                "probabilityOfPrecipitation": 20,
                "precipitation": 0
            }
        ]
    }
}
```

City name should be passed as input to both the APIs.

## DataBase Schema
### Tables
- Location - To store city name, latitude and longitude
- Current Weather Detail - To store the city's current weather. It can have only 1 record for a city at a time
- Weather Forecast Detail - To store the weather forecast data of the city for 16 days.

## Functionality
### Get current weather
- Get city name from the user as input.
- Check whether the city already exists in Location table or not.
- If it does not exist, get data from Weatherbit Current Weather API and store the city details in Location table and weather details in Current Weather table and return the data to the user.
- If location already exists in Location table,
    - If current weather data does not exist in table,  get data from Weatherbit Current Weather API and store the weather details in Current Weather table and return the data to the user.
    - If current weather data exists in table, fetch data from the table and return the result.

### Get Weather Forecast
- Get city name from the user as input.
- Check whether the city already exists in Location table or not.
- If it does not exist, get data from Weatherbit Weather Forecast API and store the city details in Location table and weather details in Weather Forecast table and return the data to the user.
- If location already exists in Location table,
    - If weather forecast data does not exist in table, get data from Weatherbit Weather Forecast API and store the weather details in Weather Forecast table and return the data to the user.
    - If forecast weather data exists in table, check whether the forecast data is populated today.
        - If so, fetch data from the table and return the result.
        - If not, update the table with latest entries and return the result.
