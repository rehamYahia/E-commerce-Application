package com.example.shoeapp

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoeapp.navigation.ButtomScreen
import kotlinx.coroutines.delay

@Composable
fun splashScreen(navController:NavController)
{
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = true)
    {
        scale.animateTo(targetValue = 0.5f ,
            animationSpec = tween(durationMillis = 500, easing = {OvershootInterpolator(2f).getInterpolation(it)}))
        //delay(3000L)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.light_grey)),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Image(painter = painterResource(id = R.drawable.logo8), contentDescription ="image", modifier = Modifier
            .fillMaxWidth()
            .height(350.dp), contentScale = ContentScale.FillWidth )

        Spacer(modifier = Modifier.padding(15.dp))
        Text(text = "BEST SHOES ,FOR THE", fontSize = 25.sp, color = colorResource(id = R.color.dark_green))
        Text(text = "BEST STYLE", fontSize = 25.sp ,color = colorResource(id = R.color.dark_green))
        Text(text = "Smart,gorgeous & fashionable ", fontSize = 18.sp, color = colorResource(id = R.color.grey))
        Text(text = "collection makes you cool", fontSize = 18.sp,color = colorResource(id = R.color.grey))

        Spacer(modifier = Modifier.padding(30.dp))

        Button(onClick = { navController.navigate("basic_page") },
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.light_green)),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(25.dp, 0.dp, 25.dp, 0.dp),
            shape =  CircleShape,

            contentPadding = PaddingValues(start = 10.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
        ) {

            Text(text = stringResource(id = R.string.getStarted), fontSize = 20.sp,
                color = colorResource(id = R.color.white),

                //modifier = Modifier.wrapContentSize(align = Alignment.Start)


                )
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.white))
                    .padding(10.dp)
                    ,
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription ="icon" ,
                    modifier = Modifier.size(20.dp)
                )
            }

        }


    }
}