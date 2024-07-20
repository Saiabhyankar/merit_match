package com.example.meritmatch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskPost(){
    val customFont= FontFamily(
            Font(R.font.sans)
        )

    Surface(
        color = Color(37,40,53,255)
    ){

    }
    Column(modifier = Modifier.offset(70.dp,100.dp)){
        Text("Post Your Tasks Here",
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = customFont)
        Text("Task",
            fontWeight = FontWeight.ExtraBold,
            color=Color.Gray,
            fontSize = 24.sp,
            modifier = Modifier.offset(y=130.dp))
        TextField(value = taskPost.value, onValueChange = { taskPost.value=it}, label = {Text("Task",
            color = Color.Gray)},
            modifier = Modifier.offset(x=-10.dp,y=150.dp))

        Text("Task description",
            fontWeight = FontWeight.ExtraBold,
            color=Color.Gray,
            fontSize = 24.sp,
            modifier = Modifier.offset(y=190.dp))
        TextField(value = taskPost.value, onValueChange = { taskPost.value=it}, label = {Text("Task Description",
            color = Color.Gray)},
            modifier = Modifier.offset(x=-10.dp,y=210.dp))

        Text("Karma Points",
            fontWeight = FontWeight.ExtraBold,
            color=Color.Gray,
            fontSize = 24.sp,
            modifier = Modifier.offset(y=250.dp))

        TextField(value = karmaPost.value, onValueChange = { karmaPost.value=it}, label = {Text("Karma Points",
            color = Color.Gray)},
            modifier = Modifier.offset(x=-10.dp,y=270.dp))
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Client.postTask(
                        postTask(userName.value, taskPost.value, karmaPost.value.toInt())
                    )
                    // Handle success
                } catch (e: Exception) {
                    // Handle error
                    println("error: ${e.message}")
                }
            }
        },
            modifier= Modifier
                .offset(-5.dp, 350.dp)
                .clip(shape = RoundedCornerShape(100))
                .size(height = 80.dp, width = 250.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(141,99,239,255))) {
                Text("Post Tasks",
                    fontSize = 28.sp)
        }

    }
}

