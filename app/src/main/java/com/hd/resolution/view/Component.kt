package com.hd.resolution.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hd.resolution.model.Resolution
import com.hd.resolution.model.icon

@Composable
@Preview(showBackground = true)
fun Resolution(
    modifier: Modifier = Modifier,
    resolution: Resolution = Resolution.FOUR_K,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = if (selected) Modifier.border(
            width = 3.dp,
            shape = RoundedCornerShape(10.dp),
            color = ButtonDefaults.buttonColors().containerColor
        ) else Modifier
    ) {
        IconButton(onClick = onClick) {
            Icon(painter = painterResource(id = resolution.icon()), "")
        }
    }
}