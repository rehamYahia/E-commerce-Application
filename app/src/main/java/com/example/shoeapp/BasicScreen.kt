package com.example.shoeapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoeapp.navigation.ButtomScreen
import com.example.shoeapp.navigation.buttomNavigationItems

private val ICON_SIZE = 24.dp





@ExperimentalFoundationApi
@Composable
fun basicScreen(navController: NavController)
{

    Box(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.white))
    )
    {
        //NavBar()

        }
        
    }

//@Preview(group = "Main", name = "Bottom bar - animated")
//@Composable
//fun NavBar()
//{
//    val navController = rememberNavController()
//
//
//    Scaffold( bottomBar ={
//        //BottomNavBar2()
//        AppButtomNavigation(navController,buttomNavigationItems)
//                         }
//
//    ) {
//        NavHost(navController = navController, startDestination = ButtomScreen.Home.route ) {
//            composable(ButtomScreen.Home.route)
//            {
//                homeScreen(navController)
//            }
//            composable(ButtomScreen.Cart.route)
//            {
//                cartScreen(navController)
//
//            }
//
//            composable(ButtomScreen.Favourit.route)
//            {
//                favouritScreen(navController)
//
//            }
//
//            composable(ButtomScreen.Profile.route)
//            {
//                personScreen(navController)
//
//            }
//        }
//    }
//
//    //Surface() {
//    //    BottomNavBar2()
//   // }
//
//}

@Composable
fun AppButtomNavigation(navController: NavController, bottomNavigationItems: Array<ButtomScreen>)
{
    BottomNavigation() {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(selected = false, onClick = {
                when(screen.route){
                    "Home" -> navController.navigate(ButtomScreen.Home.route)
                    "Favourit" -> navController.navigate(ButtomScreen.Favourit.route)
                    "Cart" -> navController.navigate(ButtomScreen.Cart.route)
                    "Profile" -> navController.navigate(ButtomScreen.Profile.route)
                }
            },
                icon = {Icon(screen.icon,contentDescription = null)},


                )
        }

    }
}
//-----------------------------------------------------------------------








