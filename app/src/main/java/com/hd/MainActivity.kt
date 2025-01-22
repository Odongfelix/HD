package com.hd

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.hd.resolution.view.Home
import com.hd.resolution.viewmodel.HomeViewModel
import com.hd.ui.theme.HDTheme

class MainActivity : ComponentActivity() {
    val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val image = BitmapFactory.decodeResource(resources, R.mipmap.test_image)
        setContent {
            HDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        homeViewModel
                    ) {
                        homeViewModel.selectedPhoto = image
                    }
                }
            }
        }
    }
}