package com.example.meritmatch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

fun SignUp(navigate:()->Unit) {
    val customFont= FontFamily(
        Font(R.font.teko)
    )
    val customFont2= FontFamily(
        Font(R.font.sforsoncasualheavy)
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .offset(y = -200.dp),
        contentAlignment = Alignment.Center
    ){
        Column {
            Text("Create Your Account",
                modifier = Modifier.offset(x=10.dp,y=-80.dp),
                fontFamily = customFont,
                fontSize = 62.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            Text("Please enter your details",
                modifier = Modifier.offset(x=30.dp,y=-80.dp),
                fontFamily = customFont2,
                fontSize = 22.sp,
                color = Color.Gray
            )
        }

        Column(modifier = Modifier.offset(x=-20.dp,y=180.dp)){
            Text("Name",
                fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.padding(4.dp))
            TextField(value = userName.value, onValueChange = { userName.value=it}, label = { Text("username" ) },
                modifier = Modifier.clickable {
                    loginUserNameClick.value=true})

            Text("Password",
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.offset(y=50.dp))
            Spacer(modifier = Modifier.padding(4.dp))
            TextField(value = password.value, onValueChange = { password.value=it}, label = { Text("password    " ) },
                modifier = Modifier
                    .clickable {
                        loginUserNameClick.value = true
                    }
                    .offset(y = 50.dp))

            Text("Confirm Password",
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.offset(y=100.dp))
            Spacer(modifier = Modifier.padding(4.dp))
            TextField(value = passwordR.value, onValueChange = { passwordR.value=it}, label = { Text("password    " ) },
                modifier = Modifier
                    .clickable {
                        loginUserNameClick.value = true
                    }
                    .offset(y = 100.dp))

            Button(onClick = {
                             navigate()
                if(password.value==passwordR.value){
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            Client.postUserDetails(
                                UserDetails(userName.value, password.value)
                            )
                            // Handle success
                        } catch (e: Exception) {
                            // Handle error
                            println("error: ${e.message}")
                        }
                    }
                }
            },
                modifier = Modifier
                    .offset(10.dp, 180.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .size(height = 70.dp, width = 250.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(41,106,220,255))){

            }
            Text(text = "Sign Up",
                modifier = Modifier.offset(x=80.dp,y=125.dp),
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold
            )

        }
    }
}
