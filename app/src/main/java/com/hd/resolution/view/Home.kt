package com.hd.resolution.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hd.R
import com.hd.resolution.model.Resolution
import com.hd.resolution.model.dimension
import com.hd.resolution.model.selectPhotosString
import com.hd.resolution.model.size
import com.hd.resolution.viewmodel.HomeViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview(showBackground = true)
fun Home(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = HomeViewModel(),
    selectPhoto: () -> Unit = {}
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (homeViewModel.selectedPhoto == null) {
            Icon(
                modifier = Modifier
                    .padding(30.dp)
                    .width(100.dp),
                painter = painterResource(R.drawable.no_photo_selected),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.height(30.dp))

            val context = LocalContext.current
            Button(onClick = selectPhoto) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = context.selectPhotosString()
                )
                Text(context.selectPhotosString())
            }
            return
        }
        homeViewModel.selectedPhoto?.apply {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .widthIn(max = 300.dp)
                    .clip(RoundedCornerShape(10.dp)),
                bitmap = this.asImageBitmap(),
                contentDescription = ""
            )
            val quality = listOf<Resolution>(
                Resolution.HD,
                Resolution.HD_PLUS,
                Resolution.FOUR_K,
                Resolution.FOUR_K_PLUS
            )

            Spacer(modifier = Modifier.height(30.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (q in quality) {
                    Resolution(
                        resolution = q, selected = q == homeViewModel.currentResolution,
                        onClick = {
                            homeViewModel.currentResolution = q
                        })
                }
            }

            if (homeViewModel.saving){
                Spacer(modifier = Modifier.height(60.dp))
                LinearProgressIndicator(progress = {
                    homeViewModel.conversionProgress
                })
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (homeViewModel.saving) return

                Column(modifier = Modifier.widthIn(max = 250.dp)) {

                    Text(
                        text = homeViewModel.currentResolution.name.replace("_", " ", true),
                        style = MaterialTheme.typography.titleLarge
                    )

                    val dimen = homeViewModel.currentResolution.dimension()
                    val formated = "%.0f".format(homeViewModel.currentResolution.size())
                    Text("${dimen.x} x ${dimen.y}")
                    Text("Requires at least ${formated}GB of storage")

                }

                Column {
                    Button({
                        homeViewModel.save()
                    }) {
                        Text("Save copy")
                    }
                }


            }


        }


    }
}