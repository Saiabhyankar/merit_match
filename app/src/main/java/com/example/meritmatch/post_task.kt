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
fun TaskPost(navigate:()->Unit){
    val customFont= FontFamily(
            Font(R.font.sans)
        )

    Column(modifier = Modifier.offset(70.dp,100.dp)){
        Text("Create New Task",
            fontSize = 32.sp,
            color = Color.Black,
            fontFamily = customFont)
        Text("Task Title",
            fontWeight = FontWeight.ExtraBold,
            color=Color.Black,
            fontSize = 24.sp,
            modifier = Modifier.offset(y=50.dp))
        TextField(value = taskPost.value, onValueChange = { taskPost.value=it}, placeholder = {Text("Task",
            color = Color.Gray)},
            modifier = Modifier.offset(x=-10.dp,y=70.dp))

        Text("Task Description",
            fontWeight = FontWeight.ExtraBold,
            color=Color.Black,
            fontSize = 24.sp,
            modifier = Modifier.offset(y=110.dp))
        TextField(value = taskDescription.value, onValueChange = { taskDescription.value=it}, placeholder = {Text("Task Description",
            color = Color.Gray)},
            modifier = Modifier.offset(x=-10.dp,y=130.dp)
                .size(width=280.dp, height = 100.dp)
        )

        Text("Karma Points",
            fontWeight = FontWeight.ExtraBold,
            color=Color.Black,
            fontSize = 24.sp,
            modifier = Modifier.offset(y=180.dp))

        TextField(value = karmaPost.value, onValueChange = { karmaPost.value=it}, placeholder = {Text("Karma Points",
            color = Color.Gray)},
            modifier = Modifier.offset(x=-10.dp,y=190.dp))
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Client.postTask(
                        postTask(userName.value, taskPost.value,karmaPost.value.toInt(), taskDescription.value)
                    )
                    // Handle success
                } catch (e: Exception) {
                    // Handle error
                    println("error: ${e.message}")
                }
            }
            navigate()
        },
            modifier= Modifier
                .offset(-15.dp, 280.dp)
                .clip(shape = RoundedCornerShape(100))
                .size(height = 60.dp, width = 290.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text("Post Tasks",
                    fontSize = 24.sp,
                    modifier = Modifier.offset(x=-5.dp))
        }

    }
}

