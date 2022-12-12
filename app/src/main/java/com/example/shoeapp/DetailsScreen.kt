package com.example.shoeapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoeapp.data.Response


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun detailsScreen(data:Response)
{

    Column(modifier = Modifier.fillMaxSize() ,

    ) {
        Image(painter = painterResource(id = R.drawable.image1) , contentDescription = "image",Modifier.size(170.dp,150.dp))
        Spacer(modifier = Modifier.padding(15.dp))
        Text(text = data.name, fontSize = 20.sp , color = colorResource(id = R.color.black) , fontWeight = FontWeight.Bold )

        Row(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(colorResource(id = R.color.light_grey)), verticalAlignment = Alignment.CenterVertically

        ) {
            Image(painter = painterResource(id = R.drawable.star_rate), contentDescription = "star",
                modifier = Modifier.size(17.dp)
            )
            Text(text = "${data.rating}", color = colorResource(id = R.color.black), fontSize = 18.sp)
            Text(text = "| ${data.number_of_rating} rating", color = colorResource(id = R.color.grey), fontSize = 17.sp)
            Text(text = data.desc , fontSize = 18.sp , color = colorResource(id = R.color.grey))
        }

        
    }
    
    

}

