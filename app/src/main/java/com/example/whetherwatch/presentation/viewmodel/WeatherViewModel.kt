package com.example.whetherwatch.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whetherwatch.data.model.WeatherUiState
import com.example.whetherwatch.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()
    
    private var hasLocationPermission = false
    
    fun setLocationPermission(granted: Boolean) {
        hasLocationPermission = granted
        if (granted) {
            loadWeather()
        } else {
            _uiState.value = WeatherUiState.PermissionRequired
        }
    }
    
    fun loadWeather() {
        if (!hasLocationPermission) {
            _uiState.value = WeatherUiState.PermissionRequired
            return
        }
        
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            
            try {
                val location = repository.getCurrentLocation()
                
                if (location == null) {
                    _uiState.value = WeatherUiState.Error("Não foi possível obter sua localização")
                    return@launch
                }
                
                val result = repository.getWeatherData(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
                
                result.fold(
                    onSuccess = { weatherData ->
                        _uiState.value = WeatherUiState.Success(weatherData)
                    },
                    onFailure = { error ->
                        _uiState.value = WeatherUiState.Error(
                            error.message ?: "Erro ao carregar dados do tempo"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(
                    e.message ?: "Erro desconhecido"
                )
            }
        }
    }
    
    fun retry() {
        loadWeather()
    }
}
