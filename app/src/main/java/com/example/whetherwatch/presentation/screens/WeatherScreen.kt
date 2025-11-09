package com.example.whetherwatch.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.example.whetherwatch.data.model.WeatherCondition
import com.example.whetherwatch.data.model.WeatherData
import kotlin.math.roundToInt

@Composable
fun WeatherScreen(weatherData: WeatherData) {
    val weatherCondition = WeatherCondition.fromCode(weatherData.weatherCode)
    
    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentPadding = PaddingValues(
            top = 32.dp,
            bottom = 32.dp,
            start = 10.dp,
            end = 10.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main temperature display
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = weatherCondition.icon,
                    fontSize = 48.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = "${weatherData.temperature.roundToInt()}°",
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
                
                Text(
                    text = weatherCondition.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 4.dp)
                )
                
                if (weatherData.locationName.isNotEmpty() && weatherData.locationName != "Unknown Location") {
                    Text(
                        text = "📍 ${weatherData.locationName}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
        
        // Feels like temperature
        item {
            WeatherInfoChip(
                icon = "🌡️",
                label = "Sensação",
                value = "${weatherData.apparentTemperature.roundToInt()}°"
            )
        }
        
        // Humidity
        item {
            WeatherInfoChip(
                icon = "💧",
                label = "Umidade",
                value = "${weatherData.humidity}%"
            )
        }
        
        // Wind speed
        item {
            WeatherInfoChip(
                icon = "💨",
                label = "Vento",
                value = "${weatherData.windSpeed.roundToInt()} km/h"
            )
        }
        
        // Precipitation (only show if > 0)
        if (weatherData.precipitation > 0) {
            item {
                WeatherInfoChip(
                    icon = "🌧️",
                    label = "Precipitação",
                    value = "${weatherData.precipitation} mm"
                )
            }
        }
    }
}

@Composable
fun WeatherInfoChip(
    icon: String,
    label: String,
    value: String
) {
    Chip(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = ChipDefaults.chipColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        enabled = false,
        label = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = icon,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = label,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    )
}
