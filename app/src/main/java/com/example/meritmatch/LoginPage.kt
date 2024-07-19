package com.example.meritmatch

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Login(navigate:()->Unit){
    val customFont= FontFamily(
        Font(R.font.firasans)
    )
    val logo= painterResource(id = R.drawable.logo)
    val login= painterResource(id = R.drawable.login)
    val view= painterResource(id = R.drawable.viewpasword)
    val detailing= painterResource(id = R.drawable.detailing)

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column (modifier = Modifier.offset(y=-180.dp)){
            Image(painter = logo, contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .offset(0.dp, -200.dp))
            Text(
                "Merit Match Authentication",
                fontSize = 30.sp,
                modifier = Modifier.offset(y = -170.dp)
            )
        }
        Column(modifier=Modifier.offset(y=70.dp)){
            Image(
                painter = login, contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .offset(y = -100.dp)
            )
            Text("Sign In",
                fontSize = 24.sp,
                modifier = Modifier.offset(y=-50.dp),
                fontFamily = customFont)
            TextField(value = userName.value, onValueChange = { userName.value=it}, label = {Text("username" )},
                modifier = Modifier.clickable {
                    loginUserNameClick.value=true})
            Spacer(modifier=Modifier.padding(16.dp))
            TextField(value = password.value, onValueChange = { password.value=it}, label = {Text("password" )},
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.clickable {
                    loginPasswordClick.value=true
                })
            Button(onClick = { },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier= Modifier
                    .offset(230.dp, -50.dp)
                    .size(45.dp)
            ) {
            }
            GetResponse()

            Button(
                onClick = {
                    if(userNameFromBackend.value.lowercase()=="found"){
                            pageNum.value=2
                            navigate()
                    }
                    else if(userNameFromBackend.value.lowercase()=="not found"){
                        pageNum.value=1
                        navigate()
                    }


                }
                ,
                shape = MaterialTheme.shapes.extraSmall,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(15.dp))
                    .size(height = 70.dp, width = 250.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(41,106,220,255))) {
                Text("Sign In",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text("Don't Have Merit-Match Account?",
                fontSize = 16.sp,
                modifier = Modifier.offset(x=20.dp))
            Button(onClick = {
                pageNum.value=1
                navigate()
                signUpOnClick.value=true},

                modifier = Modifier.offset(x=80.dp,y=10.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                Text("Sign Up",
                    color = if(signUpOnClick.value)Color(41,106,220,255)
                    else Color.Black)
            }
            Text("Made With        by Saiabhyankar",
                modifier = Modifier.offset(x = 10.dp,y=100.dp),
                fontSize = 20.sp)
            Image(painter = detailing, contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .offset(110.dp, 75.dp))
        }

    }

}