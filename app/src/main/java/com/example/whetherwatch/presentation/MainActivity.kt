package com.example.whetherwatch.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.TimeText
import com.example.whetherwatch.data.location.LocationService
import com.example.whetherwatch.data.model.WeatherUiState
import com.example.whetherwatch.data.repository.WeatherRepository
import com.example.whetherwatch.presentation.screens.*
import com.example.whetherwatch.presentation.theme.WhetherWatchTheme
import com.example.whetherwatch.presentation.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    
    private lateinit var viewModel: WeatherViewModel
    
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        viewModel.setLocationPermission(granted)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        
        // Initialize ViewModel
        val locationService = LocationService(this)
        val repository = WeatherRepository(locationService)
        viewModel = WeatherViewModel(repository)
        
        setContent {
            WearApp(
                viewModel = viewModel,
                onRequestPermission = { requestLocationPermission() }
            )
        }
        
        // Check and request permissions
        checkAndRequestPermissions()
    }
    
    private fun checkAndRequestPermissions() {
        val hasLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        if (hasLocationPermission) {
            viewModel.setLocationPermission(true)
        } else {
            viewModel.setLocationPermission(false)
        }
    }
    
    private fun requestLocationPermission() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}

@Composable
fun WearApp(
    viewModel: WeatherViewModel,
    onRequestPermission: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    WhetherWatchTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            TimeText()
            
            when (val state = uiState) {
                is WeatherUiState.Loading -> {
                    LoadingScreen()
                }
                is WeatherUiState.PermissionRequired -> {
                    PermissionScreen(onRequestPermission = onRequestPermission)
                }
                is WeatherUiState.Success -> {
                    WeatherScreen(weatherData = state.weather)
                }
                is WeatherUiState.Error -> {
                    ErrorScreen(
                        message = state.message,
                        onRetry = { viewModel.retry() }
                    )
                }
            }
        }
    }
}