package com.example.streetside.ui.theme.screens

import android.app.Application
import android.util.Log
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
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import com.example.streetside.database.User
import com.example.streetside.database.UserViewModel
import com.example.streetside.database.UserViewModelFactory
import com.example.streetside.ui.theme.Orange
import com.example.streetside.ui.theme.ubuntuFont

@Composable
fun RegisterScreen(navController: NavHostController,
                   userViewModel: UserViewModel = viewModel(factory = UserViewModelFactory(LocalContext.current.applicationContext as Application))
){
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var firstname by remember { mutableStateOf(TextFieldValue("")) }
    var surname by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpass by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "REGISTRATION",
            color = (Orange),
            fontFamily = (ubuntuFont),
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username, onValueChange = { username = it },
            label = { Text(text = "Enter Username") },

            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = firstname, onValueChange = { firstname = it },
            label = { Text(text = "Enter First Name") },

            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = surname, onValueChange = { surname = it },
            label = { Text(text = "Enter Surname") },

            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = pass, onValueChange = { pass = it },
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
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = confirmpass, onValueChange = {
                confirmpass = it
            },
            label = { Text(text = "Confirm Password") },
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
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            if (pass.text == confirmpass.text) {
                val user = User(userName = username.text, pass = pass.text,
                    firstname = firstname.text, surname = surname.text)
                userViewModel.insert(user, onSuccess = {
                    navController.navigate("login")
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    Log.d("RegisterScreen", "Creating New User")
                }, onError = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                })
            } else {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        },
            Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape
        ) {
            Text(text = "Register",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Already have an account?",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = (Orange),
            fontSize = 25.sp)
        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            navController.navigate("login")
        }, Modifier.size(width = 300.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(Orange),
            shape = RectangleShape
        ) {
            Text(text = "Go to Login",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 25.sp)
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    RegisterScreen(rememberNavController())
}