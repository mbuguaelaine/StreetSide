package com.example.streetside.ui.theme.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val logo = painterResource(id = R.drawable.blankstreetsidelogo)

    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ), label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = logo,
            contentDescription = "Logo",
            modifier = Modifier.scale(scale)
        )
    }

    // Navigate to the main screen after some delay
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate("school") {
            popUpTo("splash") { inclusive = true }
        }
    }
}

@Preview
@Composable
fun SplashPreview(){
    SplashScreen(rememberNavController())
}
