package com.example.streetside.ui.theme.screens

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import com.example.streetside.model.SharedViewModel
import com.example.streetside.ui.theme.Orange
import com.example.streetside.ui.theme.components.TabLayout
import com.example.streetside.ui.theme.ubuntuFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController


data class Schools(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    val type: College,
    val campus: String
)

enum class College {
    Uni
}

val unis = listOf(
    Schools(
        id = 25,
        name = "UoN",
        image = R.drawable.uonlogo,
        type = College.Uni,
        campus = "Chiromo Campus"
    ),
    Schools(
        id = 26,
        name = "CUEA",
        image = R.drawable.cuealogo,
        type = College.Uni,
        campus = "Main Campus"
    ),
    Schools(
        id = 27,
        name = "JKUAT",
        image = R.drawable.jkuatlogo,
        type = College.Uni,
        campus = "Karen Campus"
    ),
    Schools(
        id = 28,
        name = "STRATHMORE",
        image = R.drawable.strathlogo,
        type = College.Uni,
        campus = "Main Campus"
    ),
    Schools(
        id = 29,
        name = "USIU-A",
        image = R.drawable.usiulogo,
        type = College.Uni,
        campus = "Main Campus"
    ),
    Schools(
        id = 30,
        name = "TUK",
        image = R.drawable.tuklogo,
        type = College.Uni,
        campus = "Main Campus"
    ),
    Schools(
        id = 31,
        name = "UoN",
        image = R.drawable.uonlogo,
        type = College.Uni,
        campus = "Main Campus"
    ),
    Schools(
        id = 32,
        name = "Riara",
        image = R.drawable.riaralogo,
        type = College.Uni,
        campus = "Main Campus"
    ),
    Schools(
        id = 33,
        name = "MOI",
        image = R.drawable.moilogo,
        type = College.Uni,
        campus = "Main Campus"
    ),
    Schools(
        id = 34,
        name = "KU",
        image = R.drawable.kulogo,
        type = College.Uni,
        campus = "Nakuru Campus"
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolScreen(navController: NavController, viewModel: SharedViewModel) {
    val uiController = rememberSystemUiController()
    uiController.isStatusBarVisible = false

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "UNIVERSITIES", fontFamily = ubuntuFont)
            },
            navigationIcon = {
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(colors = ButtonDefaults.buttonColors(Color.White), onClick = {  } ) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            tint = Color.Black,
                            contentDescription = null,
                            )
                    }

                }
            }
        )
    }) { paddings ->
        Column(modifier = Modifier.padding(paddings)) {
            val selectedCollege = remember {
                mutableIntStateOf(0)
            }
            val unisState = remember {
                mutableStateListOf(*(unis).toTypedArray())
            }

            Spacer(modifier = Modifier.height(16.dp))
            TabLayout(
                items = listOf(
                    "Pick your University:" to {
                        Colleges(
                            items = unisState.filter { it.type == College.Uni },
                            navController = navController,
                            viewModel = viewModel,
                            onCollegeSelected = { schools ->
                                viewModel.selectCollege(schools)
                            }
                        )
                    },
                ),
                selectedIndex = selectedCollege.intValue,
                onTabClick = {
                    selectedCollege.intValue = it
                }
            )
        }
    }
}


fun goBack(activity: Activity) {
    activity.onBackPressed()
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Colleges(items: List<Schools>, onCollegeSelected: (Schools) -> Unit,
             navController: NavController, viewModel: SharedViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(items) { index, schools ->
                Card(
                    onClick = {
                        viewModel.selectCollege(schools)
                        onCollegeSelected(schools)
                        navController.navigate("vendor")
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
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                    }
                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            modifier= Modifier
                                .size(120.dp)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(10.dp)),
                            painter = painterResource(id = schools.image),
                            contentDescription = schools.name,
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = schools.name, textAlign = TextAlign.Center, fontSize = 15.sp, color = Color(0xFF0A0A0A))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = schools.campus, textAlign = TextAlign.Center, color = (Orange))
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun SchoolPreview(){
//    val mockViewModel = remember { mutableStateOf(SharedViewModel()) }
//    SchoolScreen(rememberNavController(), viewModel = mockViewModel.value)
//}


