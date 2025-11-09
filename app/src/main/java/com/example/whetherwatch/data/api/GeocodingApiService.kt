package com.example.whetherwatch.data.api

import com.example.whetherwatch.data.model.GeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApiService {
    
    @GET("reverse")
    suspend fun reverseGeocode(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("format") format: String = "json",
        @Query("zoom") zoom: Int = 10,
        @Query("addressdetails") addressDetails: Int = 1
    ): GeocodingResponse
}
