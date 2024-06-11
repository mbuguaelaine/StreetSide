package com.example.streetside.ui.theme.screens

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.streetside.R
import com.example.streetside.database.UserViewModel
import com.example.streetside.database.UserViewModelFactory
import com.example.streetside.model.SharedViewModel
import com.example.streetside.ui.theme.Orange
import com.example.streetside.ui.theme.ubuntuFont

@Composable
fun LoginScreen(navController: NavHostController, viewModel: SharedViewModel,
                userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(LocalContext.current.applicationContext as Application))
){
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    val context= LocalContext.current
    val userState by userViewModel.user.observeAsState()
    var showToast by remember { mutableStateOf(false) }

    LaunchedEffect(userState) {
        if (userState == null && username.text.isNotEmpty() && pass.text.isNotEmpty()) {
            showToast = true
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Login here",
            color = Orange,
            fontFamily = ubuntuFont,
            fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value =username,
            onValueChange = {username=it},
            label = { Text(text = "Enter Username") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value =pass,
            onValueChange = {pass=it},
            label = { Text(text = "Enter Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),

            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                    trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.ic_eye_open)
                else
                    painterResource(id = R.drawable.ic_eye_closed)

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, contentDescription = description)
                }
            }
        )
        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = {
            userViewModel.getUserName(username.text)
            userViewModel.user.observeForever {
                if (it?.pass == pass.text) {
                    viewModel.updateUsername(username.text)
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate("school")
                } else {
                    showToast = true
                }
            }
        }, Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape
        ) {
            Text(text = "Login",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(75.dp))

        Text(text = "Don't have an account?",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = (Orange),
            fontSize = 25.sp)
        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            navController.navigate("register")
        }, Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape
        ) {
            Text(text = "Go to Registration",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }
    }
    if (showToast) {
        LaunchedEffect(Unit) {
            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            showToast = false
        }
    }
}

//@Preview
//@Composable
//fun LoginPreview() {
//    LoginScreen(rememberNavController())
//}