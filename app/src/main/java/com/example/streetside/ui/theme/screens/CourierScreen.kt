package com.example.streetside.ui.theme.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import com.example.streetside.model.SharedViewModel
import com.example.streetside.ui.theme.Orange

@Composable
fun CourierScreen(navController: NavController, viewModel: SharedViewModel) {
    val selectedVendor by viewModel.selectedVendor.collectAsState()
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        LocalContext.current

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Your order has been ",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = (Orange),
            fontSize = 30.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "sent successfully!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = (Orange),
            fontSize = 30.sp)

        Spacer(modifier = Modifier.height(30.dp))

        Image(painter = painterResource(id = R.drawable.smiley),
            modifier = Modifier.size(250.dp),
            contentDescription = null)

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "${selectedVendor?.couriername ?: "The courier"} will deliver your order.",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = (Orange),
            fontSize = 25.sp)
        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            val u = Uri.parse("tel:" + "${selectedVendor?.couriernumber}")
                val i = Intent(Intent.ACTION_DIAL, u)
                try {
                    context.startActivity(i)
                } catch (s: SecurityException) {
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
        }, Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape
        ) {
            Text(text = "CALL COURIER",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {
            navController.navigate("rate")
        }, Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape
        ) {
            Text(text = "GO TO RATING",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(100.dp))

    }


}

@Preview
@Composable
fun CourierPreview(){
    val mockViewModel = remember { mutableStateOf(SharedViewModel(savedStateHandle = SavedStateHandle())) }
    CourierScreen(rememberNavController(), viewModel = mockViewModel.value)
}