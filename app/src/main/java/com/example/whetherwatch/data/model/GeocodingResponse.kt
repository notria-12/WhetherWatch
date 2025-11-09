package com.example.whetherwatch.data.model

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    @SerializedName("address")
    val address: Address?,
    
    @SerializedName("display_name")
    val displayName: String?
)

data class Address(
    @SerializedName("city")
    val city: String?,
    
    @SerializedName("town")
    val town: String?,
    
    @SerializedName("village")
    val village: String?,
    
    @SerializedName("municipality")
    val municipality: String?,
    
    @SerializedName("county")
    val county: String?,
    
    @SerializedName("state")
    val state: String?,
    
    @SerializedName("country")
    val country: String?
) {
    fun getCityName(): String {
        return city ?: town ?: village ?: municipality ?: county ?: state ?: country ?: "Unknown Location"
    }
}
