package com.example.streetside.ui.theme.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.streetside.R
import com.example.streetside.model.SharedViewModel
import com.example.streetside.ui.theme.Orange
import com.example.streetside.ui.theme.components.TabLayout
import com.example.streetside.ui.theme.ubuntuFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController


data class Vending(
    val id: Int,
    val name: String,
    @DrawableRes val image: Int,
    val type: Vendor,
    val owner: String,
    val phonenumber: String,
    val couriername: String,
    val couriernumber: String,
    val schoolIds: List<Int>
)

enum class Vendor {
    Vend
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorScreen(navController: NavController, viewModel: SharedViewModel) {
    val uiController = rememberSystemUiController()
    uiController.isStatusBarVisible = false

    LaunchedEffect(Unit) {
        val vends = listOf(
            Vending(
                id = 19,
                name = "Ben's Station",
                image = R.drawable.benvendor,
                type = Vendor.Vend,
                owner = "By: Ben",
                phonenumber = "254789624800",
                couriername = "Jack",
                couriernumber = "254789624800",
                schoolIds = listOf(26, 27)
            ),
            Vending(
                id = 20,
                name = "Choma Zone",
                image = R.drawable.joramvendor,
                type = Vendor.Vend,
                owner = "By: Joram",
                phonenumber = "254789624800",
                couriername = "Jude",
                couriernumber = "254789624800",
                schoolIds = listOf(31, 25)
            ),
            Vending(
                id = 21,
                name = "Sherehe Villa",
                image = R.drawable.salmavendor,
                type = Vendor.Vend,
                owner = "By: Salma",
                phonenumber = "254789624800",
                couriername = "Ronald",
                couriernumber = "254789624800",
                schoolIds = listOf(31, 25)
            ),
            Vending(
                id = 41,
                name = "Gillie's",
                image = R.drawable.gilbertvendor,
                type = Vendor.Vend,
                owner = "By: Gilbert",
                phonenumber = "254789624800",
                couriername = "Luke",
                couriernumber = "254789624800",
                schoolIds = listOf(31, 25)
            ),
            Vending(
                id = 42,
                name = "Home Fry",
                image = R.drawable.carovendor,
                type = Vendor.Vend,
                owner = "By: Caro",
                phonenumber = "254789624800",
                couriername = "Charles",
                couriernumber = "254789624800",
                schoolIds = listOf(31, 25)
            ),
            Vending(
                id = 22,
                name = "Njiva Junction",
                image = R.drawable.rachelvendor,
                type = Vendor.Vend,
                owner = "By: Rachel",
                phonenumber = "254789624800",
                couriername = "Edwina",
                couriernumber = "254789624800",
                schoolIds = listOf(26, 27)
            ),
            Vending(
                id = 43,
                name = "Farm Fresh",
                image = R.drawable.lewisvendor,
                type = Vendor.Vend,
                owner = "By: Lewis",
                phonenumber = "254789624800",
                couriername = "Lulu",
                couriernumber = "254789624800",
                schoolIds = listOf(26, 27)
            ),
            Vending(
                id = 23,
                name = "Tulishe",
                image = R.drawable.mosesvendor,
                type = Vendor.Vend,
                owner = "By: Moses",
                phonenumber = "254789624800",
                couriername = "Julia",
                couriernumber = "254789624800",
                schoolIds = listOf(28, 32)
            ),
            Vending(
                id = 44,
                name = "Schola Eats",
                image = R.drawable.scholavendor,
                type = Vendor.Vend,
                owner = "By: Scholastica",
                phonenumber = "254789624800",
                couriername = "Harry",
                couriernumber = "254789624800",
                schoolIds = listOf(28, 32)
            ),
            Vending(
                id = 24,
                name = "Kwa Mary Samaki",
                image = R.drawable.maryvendor,
                type = Vendor.Vend,
                owner = "By: Mary",
                phonenumber = "254789624800",
                couriername = "Noah",
                couriernumber = "254789624800",
                schoolIds = listOf(29, 30, 33, 34)
            ),
        )
        viewModel.setVendors(vends)
    }



    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "VENDORS", fontFamily = ubuntuFont)
            },
        )
    }) { paddings ->
        Column(modifier = Modifier.padding(paddings)) {
            val selectedVendor = remember {
                mutableIntStateOf(0)
            }

            Spacer(modifier = Modifier.height(16.dp))
            TabLayout(
                items = listOf(
                    "Choose a Vendor:" to {
                        Vendors(
                            navController = navController,
                            viewModel = viewModel,
                            onVendorSelected = { vendors ->
                                viewModel.selectVendor(vendors)
                            }
                        )
                    },
                ),
                selectedIndex = selectedVendor.intValue,
                onTabClick = {
                    selectedVendor.intValue = it
                }
            )
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Vendors(onVendorSelected: (Vending) -> Unit,
    navController: NavController, viewModel: SharedViewModel){
    val filteredVendors by viewModel.filteredVendors.collectAsState()
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
            itemsIndexed(filteredVendors) { index, vendors ->
                Card(
                    onClick = {
                        if (vendors != null) {
                            viewModel.selectVendor(vendors)
                        }
                        if (vendors != null) {
                            onVendorSelected(vendors)
                        }
                        navController.navigate("menu")
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
                            painter = painterResource(id = vendors!!.image),
                            contentDescription = vendors.name,
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = vendors.name, textAlign = TextAlign.Center, fontSize = 15.sp, color = Color(0xFF0A0A0A))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = vendors.owner, textAlign = TextAlign.Center, color = (Orange))
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun VendorPreview(){
//    val mockViewModel = remember { mutableStateOf(SharedViewModel()) }
//    VendorScreen(rememberNavController(), viewModel = mockViewModel.value)
//}


