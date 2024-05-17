package com.example.streetside.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import com.example.streetside.ui.theme.Orange


@Composable
fun HomeScreen(navController: NavController) {
//    Box(modifier = Modifier.fillMaxSize()){
//        Image(painter = painterResource(id = R.drawable.streetsidebackground),
//              contentDescription = "Background Image",
//              contentScale = ContentScale.FillBounds,
//              modifier = Modifier.matchParentSize())

//    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context= LocalContext.current

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "WELCOME TO STREETSIDE",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(900),
            color = (Orange),
//            fontFamily = (ubuntuFont),
            fontSize = 40.sp)
        Spacer(modifier = Modifier.height(200.dp))

        Button(onClick = {
            navController.navigate("login")
        },Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape) {
            Text(text = "CLICK TO LOG IN",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }
        Spacer(modifier = Modifier.height(100.dp))

        Text(text = "Don't have an account?",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = (Orange),
//            fontFamily = (ubuntuFont),
            fontSize = 25.sp)
        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            navController.navigate("register")
        }, Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape) {
            Text(text = "CLICK TO REGISTER",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(100.dp))

        Text(text = "EXIT",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(900),
            color = (Orange),
//            fontFamily = (ubuntuFont),
            fontSize = 30.sp)

    }


}

@Preview
@Composable
fun HomePreview(){
    HomeScreen(rememberNavController())
}