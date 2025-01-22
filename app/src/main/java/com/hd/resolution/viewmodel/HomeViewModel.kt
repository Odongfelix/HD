package com.hd.resolution.viewmodel

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.graphics.createBitmap
import androidx.core.graphics.get
import androidx.core.graphics.set
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hd.resolution.model.Resolution
import com.hd.resolution.model.dimension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var selectedPhoto: Bitmap? by mutableStateOf(null)
    var conversionProgress by mutableFloatStateOf(0f)
    var currentResolution by mutableStateOf(Resolution.HD)
    var saving by mutableStateOf(false)


    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            saving = true
            val dimen = currentResolution.dimension()
            val temp = createBitmap(dimen.x, dimen.y)
            for (x in 0 until dimen.x) {
                conversionProgress = x.toFloat() / (dimen.x - 1)
                for (y in 0 until dimen.y) {
                    selectedPhoto?.let {
                    if (x < it.width && y < it.height)
                    temp[x, y] = it[x, y]
                    }
                }
            }
            saving = false
            selectedPhoto = temp
        }
    }
}