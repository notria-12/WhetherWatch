package com.example.whetherwatch.data.model

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    object PermissionRequired : WeatherUiState()
    data class Success(val weather: WeatherData) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

data class WeatherData(
    val temperature: Double,
    val apparentTemperature: Double,
    val humidity: Int,
    val weatherCode: Int,
    val windSpeed: Double,
    val precipitation: Double,
    val locationName: String = ""
)

// Weather code mapping based on Open-Meteo API
enum class WeatherCondition(val code: Int, val description: String, val icon: String) {
    CLEAR(0, "Céu limpo", "☀️"),
    MAINLY_CLEAR(1, "Parcialmente limpo", "🌤️"),
    PARTLY_CLOUDY(2, "Parcialmente nublado", "⛅"),
    OVERCAST(3, "Nublado", "☁️"),
    FOG(45, "Neblina", "🌫️"),
    DEPOSITING_RIME_FOG(48, "Neblina congelante", "🌫️"),
    LIGHT_DRIZZLE(51, "Garoa leve", "🌦️"),
    MODERATE_DRIZZLE(53, "Garoa moderada", "🌦️"),
    DENSE_DRIZZLE(55, "Garoa forte", "🌧️"),
    LIGHT_FREEZING_DRIZZLE(56, "Garoa congelante leve", "🌧️"),
    DENSE_FREEZING_DRIZZLE(57, "Garoa congelante forte", "🌧️"),
    SLIGHT_RAIN(61, "Chuva leve", "🌧️"),
    MODERATE_RAIN(63, "Chuva moderada", "🌧️"),
    HEAVY_RAIN(65, "Chuva forte", "⛈️"),
    LIGHT_FREEZING_RAIN(66, "Chuva congelante leve", "🌧️"),
    HEAVY_FREEZING_RAIN(67, "Chuva congelante forte", "🌧️"),
    SLIGHT_SNOW(71, "Neve leve", "🌨️"),
    MODERATE_SNOW(73, "Neve moderada", "❄️"),
    HEAVY_SNOW(75, "Neve forte", "❄️"),
    SNOW_GRAINS(77, "Granizo de neve", "❄️"),
    SLIGHT_RAIN_SHOWERS(80, "Pancadas de chuva leves", "🌦️"),
    MODERATE_RAIN_SHOWERS(81, "Pancadas de chuva moderadas", "🌧️"),
    VIOLENT_RAIN_SHOWERS(82, "Pancadas de chuva violentas", "⛈️"),
    SLIGHT_SNOW_SHOWERS(85, "Pancadas de neve leves", "🌨️"),
    HEAVY_SNOW_SHOWERS(86, "Pancadas de neve fortes", "❄️"),
    THUNDERSTORM(95, "Tempestade", "⛈️"),
    THUNDERSTORM_SLIGHT_HAIL(96, "Tempestade com granizo leve", "⛈️"),
    THUNDERSTORM_HEAVY_HAIL(99, "Tempestade com granizo forte", "⛈️");

    companion object {
        fun fromCode(code: Int): WeatherCondition {
            return values().find { it.code == code } ?: CLEAR
        }
    }
}
