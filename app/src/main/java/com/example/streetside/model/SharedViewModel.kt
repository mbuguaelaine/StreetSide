package com.example.streetside.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.streetside.ui.theme.screens.Food
import com.example.streetside.ui.theme.screens.Schools
import com.example.streetside.ui.theme.screens.Vending
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {
    private val _likedItems = MutableStateFlow<List<Food>>(emptyList())
    val likedItems: StateFlow<List<Food>> = _likedItems

    private val _selectedVendor = MutableStateFlow<Vending?>(null)
    val selectedVendor: StateFlow<Vending?> get() = _selectedVendor

    private val _selectedCollege = MutableStateFlow<Schools?>(null)
    val selectedCollege: StateFlow<Schools?> get() = _selectedCollege

    private val _foodQuantities = MutableStateFlow<Map<Food, Int>>(emptyMap())
    val foodQuantities: StateFlow<Map<Food, Int>>  = _foodQuantities

    private val _totalPrice = MutableStateFlow(100.0)
    val totalPrice: StateFlow<Double> get() = _totalPrice

//    private val _customName = mutableStateOf<User?>(null)
//    val customName: State<User?> get() = _customName
//
//    private val _userDetails = mutableStateOf<User?>(null)
//    val userDetails: State<User?> = _userDetails

    fun addItem(food: Food) {
        if (_likedItems.value.none { it.id == food.id }) {
            _likedItems.value += food.copy(liked = true)
            Log.d("SharedViewModel", "Adding item: $food")
        }
    }

    fun removeItem(food: Food) {
        if (_likedItems.value.any { it.id == food.id }) {
            _likedItems.value = _likedItems.value.filter { it.id != food.id }
            Log.d("SharedViewModel", "Removing item: $food")
        }
    }

    fun refreshLikedItems() {
        _likedItems.value = emptyList()
        calculateTotalPrice()
        Log.d("SharedViewModel", "Refreshing liked items")
    }

    fun selectVendor(vendors: Vending) {
        _selectedVendor.value = vendors
    }

    fun selectCollege(schools: Schools) {
        _selectedCollege.value = schools
    }

    fun updateQuantity(food: Food, quantity: Int) {
        _foodQuantities.value = _foodQuantities.value.toMutableMap().apply {
            put(food, quantity)
        }
        calculateTotalPrice()
    }

    fun getQuantity(food: Food): Int {
        return _foodQuantities.value[food] ?: 1
    }

    fun calculateTotalPrice() {
        val itemTotal = _likedItems.value.sumOf { it.price.toDouble() * getQuantity(it) }
        val deliveryFee = 100.0
        _totalPrice.value = itemTotal + deliveryFee
    }

//    fun getUserByUsername(username: String, callback: (User?) -> Unit) {
//        viewModelScope.launch {
//            val user = userRepository.getUserByUsername(username)
//            callback(user)
//            _customName.value = user
//        }
//    }

//    fun fetchUserDetails(username: String) {
//        viewModelScope.launch {
//            val user = userRepository.getUserByUsername(username)
//            _customName.value = user
//        }
//    } ${name?.firstname} ${name?.surname} (for the whatsapp part)

    fun sendOrderViaWhatsApp(items: List<Food>, vendor: Vending?, context: Context) {
        vendor?.let {
            val orderDetails = items.joinToString(separator = "\n") { food ->
                "${food.name} x ${getQuantity(food)} = Kshs ${"%.2f".format(food.price.toDouble() * getQuantity(food))}"
            }
//            val name = customName.value
            val total = totalPrice.value
            val schools = selectedCollege.value
            val message = "Order Details:\n$orderDetails\n\nTotal: Kshs ${"%.2f".format(total)}\n\nDeliver To: ${schools?.name} - ${schools?.campus}\n\nCustomer Name:"

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://wa.me/${vendor.phonenumber}?text=${Uri.encode(message)}")
                setPackage("com.whatsapp")
            }
            try {
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(context, "Vendor not selected", Toast.LENGTH_SHORT).show()
        }
    }

//    fun rateVendor(vendorName: String, rating: Int, context: Context) {
//        viewModelScope.launch {
//            val vendorRating = Ratings(vendorName = vendorName, rating = rating)
//            userRepository.insertRating(vendorRating)
//            Toast.makeText(context, "Rating recorded", Toast.LENGTH_SHORT).show()
//        }
//    }
}