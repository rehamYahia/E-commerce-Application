package com.example.shoeapp.navigation


import androidx.compose.material.TextFieldColorsWithIcons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ButtomScreen(val route:String , val title:String , val icon:ImageVector )
{
    object Home:ButtomScreen("Home","Home", Icons.Filled.Home)
    object Favourit:ButtomScreen("Favourit","Favourit",Icons.Filled.Favorite)
    object Cart:ButtomScreen("Cart","Cart",Icons.Filled.ShoppingCart)
    object Profile:ButtomScreen("Profile","Profile",Icons.Filled.Person)

}
val buttomNavigationItems = arrayOf(
    ButtomScreen.Home,
    ButtomScreen.Favourit,
    ButtomScreen.Cart,
    ButtomScreen.Profile
)