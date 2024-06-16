package com.example.streetside.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.streetside.R
import com.example.streetside.model.SharedViewModel
import com.example.streetside.ui.theme.Orange
import com.example.streetside.ui.theme.components.TabLayout
import com.example.streetside.ui.theme.ubuntuFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavHostController, viewModel: SharedViewModel, showPrompt: Boolean) {
    val likedItems by viewModel.likedItems.collectAsState(initial = listOf())
    var showDialog by remember { mutableStateOf(showPrompt) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                navController.navigate("menu") {
                    popUpTo("order") { inclusive = true }
                }
            },
            title = { Text(text = "Delivery Confirmation") },
            text = { Text(text = "The standard Kshs 100 delivery fee has automatically been added to your total.") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Proceed")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                        navController.navigate("menu") {
                            popUpTo("order") { inclusive = true }
                        }
                    }
                ) {
                    Text("Go Back")
                }
            }
        )
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "ORDER", fontFamily = ubuntuFont)
            },
            navigationIcon = {
                Row {
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(colors = ButtonDefaults.buttonColors(Color.White),
                           onClick = {viewModel.refreshLikedItems()  }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            tint = Color.Black,
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

            val onLikeChange: (Food) -> Unit = { food ->

                    if (food.liked) R.drawable.ticked else R.drawable.unticked

            }

            Spacer(modifier = Modifier.height(16.dp))
            TabLayout(
                items = listOf(
                    "Confirm your Order:" to {
                        Orders(
                            items = likedItems,
                            onLikeChange = onLikeChange,
                            onTap = {},
                            onQuantityChange = { _, _ -> },
                            viewModel = viewModel,
                            onConfirmOrder = {},
                            navController = navController
                        )
                    }
                ),
                selectedIndex = selectedFoodType.intValue,
                onTabClick = {
                    selectedFoodType.intValue = it
                }
            )
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Orders(items: List<Food>, onLikeChange: (Food) -> Unit,
               onTap: (Food) -> Unit, onQuantityChange: (Food, Int) -> Unit,
               viewModel: SharedViewModel, onConfirmOrder: () -> Unit, navController: NavController) {
        val context = LocalContext.current
        val totalPrice by viewModel.totalPrice.collectAsState()

        LaunchedEffect(items) {
            viewModel.calculateTotalPrice()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(items) { index, food ->

                    val drawableId = if (food.liked) R.drawable.ticked else R.drawable.unticked
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
                            Image(
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable {
                                        onLikeChange(food)
                                    },
                                painter = painterResource(
                                    id = drawableId
                                ),
                                contentDescription = null
                            )

                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(text = food.name, fontSize = 15.sp, color = Color.Black)
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = "Kshs ${"%.2f".format(food.price)}", color = (Orange))
                            Spacer(modifier = Modifier.height(2.dp))
                            @Composable
                            fun QuantitySelector(
                                food: Food,
                                onQuantityChange: (Food, Int) -> Unit,
                                viewModel: SharedViewModel
                            ) {
                                var quantity by remember { mutableIntStateOf(viewModel.getQuantity(food)) }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Quantity: ",
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                    IconButton(
                                        onClick = {
                                            if (quantity > 1) {
                                                quantity--
                                                onQuantityChange(food, quantity)
                                                viewModel.updateQuantity(food, quantity)
                                            }
                                        },
                                        modifier = Modifier.size(30.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.ArrowBack,
                                            contentDescription = "Decrease quantity",
                                            tint = Color.Black
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "$quantity",
                                        fontSize = 16.sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    IconButton(
                                        onClick = {
                                            if (quantity < 6) {
                                                quantity++
                                                onQuantityChange(food, quantity)
                                                viewModel.updateQuantity(food, quantity)
                                            }
                                        },
                                        modifier = Modifier.size(30.dp)
                                    ) {
                                        Icon(
                                            Icons.Default.ArrowForward,
                                            contentDescription = "Increase quantity",
                                            tint = Color.Black
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        text = "Total: Kshs ${"%.2f".format(food.price.toDouble() * quantity)}",
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )

                                }
                            }
                            QuantitySelector(food = food, onQuantityChange = onQuantityChange, viewModel = viewModel)
                        }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {},
                Modifier
                    .size(width = 300.dp, height = 60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Orange),
                shape = RectangleShape)
            {
                Text(
                    text = "Total: Kshs ${"%.2f".format(totalPrice)}",
                    color = Color.Black,
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    onConfirmOrder()
                    viewModel.sendOrderViaWhatsApp(items, viewModel.selectedVendor.value, context)
                    navController.navigate("courier")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Orange)
            ) {
                Text(text = "Confirm and Send Order",
                    color = Color.Black)
            }
            Spacer(modifier = Modifier.height(16.dp))
            }
        }


@Preview
@Composable
fun OrderPreview(){
    val mockViewModel = remember { mutableStateOf(SharedViewModel(savedStateHandle = SavedStateHandle())) }
    OrderScreen(rememberNavController(), viewModel = mockViewModel.value, showPrompt = false)
}