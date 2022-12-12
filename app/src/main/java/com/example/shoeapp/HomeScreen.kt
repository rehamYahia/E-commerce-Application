package com.example.shoeapp

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.shoeapp.data.Response
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val db = Firebase.firestore


@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun homeScreen(navController: NavController)
{

    val context = LocalContext.current
    val dataFileString= getJsonDataFromAsset(context,"Data.json")
    val gson = Gson()
    val listSampleType = object: TypeToken<List<Response>>(){}.type
    var sampleData :List<Response>  =gson.fromJson(dataFileString,listSampleType)
    //firebase






    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Spacer(modifier = Modifier.padding(12.dp))
        searchBar(hint="Search", modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){

        }
        Spacer(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp))
        test()

        Scrollhor(list = listOf("shoses " , "LapTop" , "Sports" , "Phone" ,"Watches"))

        LazyVerticalGrid(columns = GridCells.Fixed(2) ,
            modifier = Modifier.padding(10.dp)
        ) {
            items(sampleData){ data ->
                cardScreen(data, navController)

            }

        }
        
        /*LazyVerticalGrid(
            cells = GridCells.Fixed(2), modifier = Modifier
                .padding(10.dp)
        )
        {
            items(sampleData){ data ->
                cardScreen(data, navController)
                
            }*/

        }
    }


@Composable
fun cardScreen(data: Response, navController: NavController)
{
    val fav_stutes = rememberSaveable { mutableStateOf(false) }
    val cart_status = rememberSaveable { mutableStateOf(false) }

    var itemToFav:HashMap<String,String>
    Card(modifier = Modifier
        .clickable {
            val itemVal = Gson().toJson(data)
            navController.navigate("details_page/$itemVal")
        }
        .padding(5.dp)
        .size(250.dp, 350.dp),
        elevation=5.dp,
        shape = RoundedCornerShape(7.dp)
    ) {
        Column(modifier = Modifier.background(colorResource(id = R.color.light_grey))) {
            Image(painter = rememberImagePainter(data = "${data.url}"), contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(colorResource(id = R.color.light_grey)),
                contentScale = ContentScale.Fit


            )

            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "${data.name}", fontSize = 18.sp, color = colorResource(id = R.color.dark_green) , modifier = Modifier.padding(5.dp))
            Spacer(modifier = Modifier.padding(5.dp))
            Row(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(5.dp)
                .background(colorResource(id = R.color.light_grey)), verticalAlignment = Alignment.CenterVertically

            ) {
                Image(painter = painterResource(id = R.drawable.star_rate), contentDescription = "star",
                    modifier = Modifier.size(17.dp)
                )
                Text(text = "${data.rating}", color = colorResource(id = R.color.light_green), fontSize = 18.sp)
                Text(text = "| ${data.number_of_rating} rating", color = colorResource(id = R.color.black), fontSize = 17.sp)
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

                )
            {
                Text(text = "${data.price_befor_offer - data.offer * data.price_befor_offer / 100 } $", fontSize = 20.sp, color = colorResource(
                    id = R.color.red) , fontWeight = FontWeight.Bold)
                Row() {
                    Text(text = "${data.price_befor_offer} $" , fontSize = 16.sp , color = colorResource(
                        id = R.color.light_green))
                    Text(text = " (${data.offer} % offer)" , fontSize = 16.sp ,color = colorResource(
                        id = R.color.light_green) )
                }

            }


            Row() {
                IconButton(
                    onClick = {
                        fav_stutes.value = !fav_stutes.value
                       if (fav_stutes.value)
                       {
                           itemToFav = hashMapOf("name" to "${data.name}",
                                                    "price" to "${data.price_befor_offer}"
                           )
                           db.collection("Favourit")
                               .add(itemToFav)
                               .addOnSuccessListener {
                                   Log.d(TAG, "item is added sucessfully:")

                               }
                               .addOnFailureListener{
                                   Log.w(TAG, "Error ")
                               }
                       }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = "favourit",
                        tint = if(fav_stutes.value) Color.Red else Color.Gray
                    )
                }

                IconButton(onClick = { cart_status.value = !cart_status.value }) {
                    Icon(painter = painterResource(id = R.drawable.ic_shopping_cart),
                        contentDescription = "cart",
                        tint = if(cart_status.value) colorResource(id = R.color.orange) else Color.Gray
                    )

                }



            }
        }

    }

}

fun getJsonDataFromAsset(context: Context, data:String):String
{
    return context.assets.open(data).bufferedReader().use { it.readText() }
}






//--------------------------------------------------------------------------
@Composable
fun searchBar(
    modifier: Modifier = Modifier,
    hint:String,
    onSearch : (String) -> Unit = {}
){
    var text by remember{ mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier=Modifier) {
        BasicTextField(value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color=Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    //isHintDisplayed = it != FocusState.
                }
        )
        if(isHintDisplayed)
        {
            Text(text = hint , color = Color.LightGray , modifier = Modifier.padding(horizontal = 20.dp , vertical = 12.dp))
        }

    }
}

@Composable
fun liner()
{
    val lightColor:Color
    val mediamColor:Color
    val darkColor:Color
    
    BoxWithConstraints(modifier = Modifier
        .padding(7.5.dp)
        .aspectRatio(0.5f)
        .clip(RoundedCornerShape(10))
        .background(colorResource(id = R.color.dark_green))
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight
        //medium color path
        val meduimColorPoint1 = Offset(0f, height * 0.3f)
        val meduimColorPoint2 = Offset(width * 0.1f, height * 0.35f)
        val meduimColorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val meduimColorPoint4 = Offset(width * 0.75f, height * 0.7f)
        val meduimColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val meduimColorPath = Path().apply {
            moveTo(meduimColorPoint1.x, meduimColorPoint1.y)
            standardQuadFromTo(meduimColorPoint1, meduimColorPoint2)
            standardQuadFromTo(meduimColorPoint2, meduimColorPoint3)
            standardQuadFromTo(meduimColorPoint3, meduimColorPoint4)
            standardQuadFromTo(meduimColorPoint4, meduimColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        //light color path
        val lightColorPoint1 = Offset(0f, height * 0.3f)
        val lightColorPoint2 = Offset(width * 0.1f, height * 0.35f)
        val lightColorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val lightColorPoint4 = Offset(width * 0.75f, height * 0.7f)
        val lightColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val lightColorPath = Path().apply {
            moveTo(meduimColorPoint1.x, meduimColorPoint1.y)
            standardQuadFromTo(lightColorPoint1, lightColorPoint2)
            standardQuadFromTo(lightColorPoint2, lightColorPoint3)
            standardQuadFromTo(lightColorPoint3, lightColorPoint4)
            standardQuadFromTo(lightColorPoint4, lightColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
       Canvas(modifier = Modifier.fillMaxSize())
       {
           drawPath(
               path = meduimColorPath,
               color = Color.Red
           )

           drawPath(
               path = lightColorPath,
               color = Color.Yellow
           )
       }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp))
        {
            Text(text = "NICK AIR FORCES",
                style = MaterialTheme.typography.h4,
                lineHeight = 1.8.sp,
                modifier = Modifier.align(Alignment.TopStart), color = Color.White
            )
            Text(text = "Show More",
                style = MaterialTheme.typography.body1,
                lineHeight = 1.6.sp,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Image(painter = painterResource(id = R.drawable.image1), contentDescription = "image",
                modifier=Modifier.align(Alignment.CenterEnd)
            )
        }
        
    }

}

@Composable
fun Scrollhor(list:List<String>)
{
    var selectionItem by remember {mutableStateOf(0) }
    LazyRow{
        items(list.size){
            Box(
                modifier = Modifier
                    .padding(
                        start = 15.dp,
                        top = 10.dp,
                        end = 7.dp,
                        bottom = 7.dp
                    )
                    .clickable {
                        selectionItem = it
                    }
                    .clip(RoundedCornerShape(35.dp))
                    .background(
                        if (selectionItem == it) colorResource(id = R.color.dark_green)
                        else colorResource(id = R.color.grey)
                    )
                    .padding(vertical = 10.dp, horizontal = 17.dp)




            )
            {
                Text(text = list[it] , color = Color.White , fontSize = 20.sp )
            }
        }
    }
}


@Composable
fun test()
{
    Row(modifier = Modifier
        .padding(start = 12.dp, top = 7.dp, end = 12.dp, bottom = 7.dp)
        .fillMaxWidth()
        .height(120.dp)
        .clip(RoundedCornerShape(13.dp))
        .background(colorResource(id = R.color.dark_green))
        .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Column() {
            Text(text = "NICK AIR FORCES" ,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 25.sp ,
                style = MaterialTheme.typography.h2
            )
            Text(text = "Show More" ,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 17.sp ,
                style = MaterialTheme.typography.body1
            )
        }

        Image(painter = painterResource(id = R.drawable.image1), contentDescription ="shoes" , modifier = Modifier
            .width(250.dp)
            .height(300.dp)
            .scale(2f)
        )

    }
}



