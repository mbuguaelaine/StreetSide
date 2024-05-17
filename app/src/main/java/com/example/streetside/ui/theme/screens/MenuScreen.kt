package com.example.streetside.ui.theme.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import com.example.streetside.ui.theme.components.TabLayout
import com.example.streetside.ui.theme.ubuntuFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController


data class Food(
    val name: String,
    @DrawableRes val image: Int,
    val type: FoodType,
    val liked: Boolean = false,
    val price: String
)

enum class FoodType {
    Meal, Side, Snack
}

val foods = listOf(
    Food(
        name = "Ugali",
        image = R.drawable.ugali,
        type = FoodType.Meal,
        price = "100"
    ),
    Food(
        name = "Githeri",
        image = R.drawable.githeri,
        type = FoodType.Meal,
        price = "150"
    ),
    Food(
        name = "Mukimo",
        image = R.drawable.mukimo,
        type = FoodType.Meal,
        price = "150"
    ),
    Food(
        name = "Pilau",
        image = R.drawable.pilau,
        type = FoodType.Meal,
        price = "150"
    ),
    Food(
        name = "Beef Stew",
        image = R.drawable.beefstew,
        type = FoodType.Meal,
        price = "200"
    ),
    Food(
        name = "Chicken Stew",
        image = R.drawable.chickenstew,
        type = FoodType.Meal,
        price = "250"
    ),
    Food(
        name = "Apple",
        image = R.drawable.apple,
        type = FoodType.Side,
        price = "50"
    ),
    Food(
        name = "Orange",
        image = R.drawable.orange,
        type = FoodType.Side,
        price = "30"
    ),
    Food(
        name = "Watermelon",
        image = R.drawable.waterslice,
        type = FoodType.Side,
        price = "20"
    ),
    Food(
        name = "Pineapple",
        image = R.drawable.pineslice,
        type = FoodType.Side,
        price = "20"
    ),
    Food(
        name = "Mango",
        image = R.drawable.mango,
        type = FoodType.Side,
        price = "100"
    ),
    Food(
        name = "Fruit Salad",
        image = R.drawable.fruitsalad,
        type = FoodType.Side,
        price = "150"
    ),
    Food(
        name = "Smocha",
        image = R.drawable.smocha,
        type = FoodType.Snack,
        price = "60"
    ),
    Food(
        name = "Mutura",
        image = R.drawable.mutura,
        type = FoodType.Snack,
        price = "30"
    ),
    Food(
        name = "Smokie Pasua",
        image = R.drawable.smokiepasua,
        type = FoodType.Snack,
        price = "40"
    ),
    Food(
        name = "Mayai Pasua",
        image = R.drawable.mayaipasua,
        type = FoodType.Snack,
        price = "30"
    ),
    Food(
        name = "Bhajias",
        image = R.drawable.bhajias,
        type = FoodType.Snack,
        price = "100"
    ),
    Food(
        name = "Chips Mwitu",
        image = R.drawable.chipsmwitu,
        type = FoodType.Snack,
        price = "100"
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    val uiController = rememberSystemUiController()
    uiController.isStatusBarVisible = false

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Our Menu", fontFamily = ubuntuFont)
            },
            navigationIcon = {
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(colors = ButtonDefaults.buttonColors(Color.Black), onClick = { finishAffinity() } ) {
                        Icon(imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }

                }
            }
        )
    }) { paddings ->
        Column(modifier = Modifier.padding(paddings)) {
            val selectedFoodType = remember {
                mutableIntStateOf(0)
            }
            val foodsState = remember {
                mutableStateListOf(*(foods).toTypedArray())
            }
            val onLikeChange: (Food) -> Unit = {
                foodsState[foodsState.indexOf(it)] =
                    foodsState[foodsState.indexOf(it)].copy(liked = !it.liked)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TabLayout(
                items = listOf(
                    "Meals" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Meal },
//                            onLikeChange = onLikeChange,
                            onLikeChange = { navController.navigate("order")},
                            onTap = {
//                                navController.navigate("food")
                            }
                        )
                    },
                    "Fruits" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Side },
//                            onLikeChange = onLikeChange,
                            onLikeChange = { navController.navigate("order")},
                            onTap = {
//                                navController.navigate("food")
                            }
                        )
                    },
                    "Snacks" to {
                        Foods(
                            items = foodsState.filter { it.type == FoodType.Snack },
//                            onLikeChange = onLikeChange,
                            onLikeChange = { navController.navigate("order")},
                            onTap = {
//                                navController.navigate("food")
                            }
                        )
                    },
                ),
                selectedIndex = selectedFoodType.intValue,
                onTabClick = {
                    selectedFoodType.intValue = it
                }
            )
        }
    }
}

fun finishAffinity() {
    TODO("Not yet implemented")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Foods(items: List<Food>, onLikeChange: (Food) -> Unit, onTap: (Food) -> Unit) {
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
            itemsIndexed(items) { index, food ->
                Card(
                    onClick = {
                        onTap(food)
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
//                        Image(
//                            modifier = Modifier
//                                .size(25.dp)
//                                .clickable {
//                                    onLikeChange(food)
//                                },
//                            painter = painterResource(
//                                id =
//                                if (food.liked) R.drawable.ic_like else R.drawable.ic_unlike
//                            ),
//                            contentDescription = null
//                        )
                    }
                    Column(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            modifier= Modifier
                                .size(120.dp)
                                .aspectRatio(1f)
                                .clip(
                                    RoundedCornerShape(10.dp)),
                            painter = painterResource(id = food.image),
                            contentDescription = food.name,
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = food.name, fontSize = 15.sp, color = Color(0xff383838))
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = "Kshs ${food.price}")
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MenuPreview(){
    MenuScreen(rememberNavController())
}


