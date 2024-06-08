package com.example.streetside.ui.theme.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import com.example.streetside.database.Ratings
import com.example.streetside.database.UserViewModel
import com.example.streetside.database.UserViewModelFactory
import com.example.streetside.model.SharedViewModel
import com.example.streetside.ui.theme.Orange


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingScreen(navController: NavHostController, viewModel: SharedViewModel,
                 userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(LocalContext.current.applicationContext as Application))
) {
    val selectedVendor by viewModel.selectedVendor.collectAsState()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.size(150.dp))
        Text(
            text = "Thank you for ordering ",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = (Orange),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "from ${selectedVendor?.name ?: "us"}! ",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = (Orange),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.size(150.dp))
        Text(
            text = "Rate your Vendor: ",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = (Orange),
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.size(40.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                onClick = {
                    val rating = Ratings(vendorName = selectedVendor?.name ?: "Vendor", rating = 1) //A good rating is equivalent to 1
                    userViewModel.insertRating(rating,
                        onSuccess = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
                        onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
                          },
                elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(20.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        modifier = Modifier
                            .size(120.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(10.dp)),
                        painter = painterResource(id = R.drawable.thumbsup),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier.size(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }

            Card(
                onClick = {
                    val rating = Ratings(vendorName = selectedVendor?.name ?: "Vendor", rating = 0) //A bad rating is equivalent to 0
                    userViewModel.insertRating(rating,
                        onSuccess = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
                        onError = { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() })
                },
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(20.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Image(
                        modifier = Modifier
                            .size(120.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(10.dp)),
                        painter = painterResource(id = R.drawable.thumbsdown),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier.size(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
    }
}

@Preview
@Composable
fun RatingPreview(){
    val mockViewModel = remember { mutableStateOf(SharedViewModel()) }
    RatingScreen(rememberNavController(), viewModel = mockViewModel.value)
}
