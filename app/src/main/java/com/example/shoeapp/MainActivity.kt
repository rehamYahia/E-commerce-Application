package com.example.shoeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navigatePage()

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun navigatePage()
{
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_page" ){

        composable("splash_page" , content = {splashScreen(navController= navController)})
        composable("basic_page" , content = {basicScreen(navController= navController)})
       // composable("favourit_page" , content = { favouritScreen(navController = navController)})
     //   composable("cart_page" , content = { cartScreen(navController = navController)})
      //  composable("person_page" , content = { personScreen(navController = navController)})




        /*composable("details_page/{itemVal}" , arguments = listOf(
            navArgument("itemVal"){
                type = NavType.StringType
            }), )

        {
                navBackStackEntry -> navBackStackEntry?.arguments?.getString("itemVal")?.let{json ->
            val itemVal = Gson().fromJson(json,Response::class.java)
            detailsScreen(data=itemVal)

        }
        }*/
    }



}

/*@Composable
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
            label = { Text(text = screen.route)},

        )
    }

}
}*/




