package com.example.whetherwatch.data.repository

import android.location.Location
import com.example.whetherwatch.data.api.RetrofitClient
import com.example.whetherwatch.data.location.LocationService
import com.example.whetherwatch.data.model.WeatherData
import com.example.whetherwatch.data.model.WeatherResponse

class WeatherRepository(private val locationService: LocationService) {
    
    private val weatherApi = RetrofitClient.weatherApi
    private val geocodingApi = RetrofitClient.geocodingApi
    
    suspend fun getCurrentLocation(): Location? {
        return try {
            locationService.getCurrentLocation()
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getWeatherData(latitude: Double, longitude: Double): Result<WeatherData> {
        return try {
            val weatherResponse = weatherApi.getWeather(latitude, longitude)
            val cityName = getCityName(latitude, longitude)
            Result.success(mapToWeatherData(weatherResponse, cityName))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private suspend fun getCityName(latitude: Double, longitude: Double): String {
        return try {
            val response = geocodingApi.reverseGeocode(latitude, longitude)
            response.address?.getCityName() ?: "Unknown Location"
        } catch (e: Exception) {
            "Unknown Location"
        }
    }
    
    private fun mapToWeatherData(response: WeatherResponse, cityName: String): WeatherData {
        return WeatherData(
            temperature = response.current.temperature,
            apparentTemperature = response.current.apparentTemperature,
            humidity = response.current.humidity,
            weatherCode = response.current.weatherCode,
            windSpeed = response.current.windSpeed,
            precipitation = response.current.precipitation,
            locationName = cityName
        )
    }
}
