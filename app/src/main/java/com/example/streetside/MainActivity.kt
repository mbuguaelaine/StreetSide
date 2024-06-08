package com.example.streetside

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.streetside.ui.theme.Navigation
import com.example.streetside.ui.theme.StreetsideTheme

class MainActivity : ComponentActivity() {
//    private lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val userDao = UserDatabase.getDatabase(applicationContext).userDao()
//        val ratingDao = UserDatabase.getDatabase(applicationContext).ratingDao()
//        userRepository = UserRepository(userDao, ratingDao)
//        val sharedViewModel: SharedViewModel by viewModels {
//            SharedViewModelFactory(userRepository)
//        }

        setContent {
            StreetsideTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    Navigation()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SSPreview() {
    StreetsideTheme {
    }
}