package com.example.meritmatch



import android.R.attr.x
import android.R.attr.y
import android.R.color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun Transaction(){
    GetResponse()
    val customFont= FontFamily(
         Font( R.font.generica)
    )
    val customFont1= FontFamily(
        Font( R.font.coverface)
    )
    val date =LocalDate.now()
    val time=LocalTime.now()
    val add = painterResource(id = R.drawable.add)
    Column(modifier = Modifier.fillMaxSize()) {
        Card( colors = CardDefaults.cardColors(containerColor = Color(53, 0, 107, 255)),
            shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 30.dp),
            modifier = Modifier.size(500.dp)
        ) {
            if(time.hour in 6..12){
                Text("Good Morning ,",
                    modifier=Modifier.offset(50.dp,100.dp),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            else if (time.hour in 12..18){
                Text("Good Afternoon ,",
                    modifier=Modifier.offset(50.dp,100.dp),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            else{
                Text("Good Evening ,",
                    modifier=Modifier.offset(50.dp,100.dp),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.ExtraBold)
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = date.dayOfMonth.toString() +" "+ date.month.toString() + " " + date.year.toString(),
                modifier = Modifier.offset(50.dp,100.dp),
                color=Color.Gray,
                fontSize = 20.sp)
            Text("Total Karma Points",
                modifier= Modifier
                    .offset(50.dp, 180.dp)
                    .padding(0.dp),
                color=Color.Gray,
                fontSize = 20.sp)
            Text(karmaPoint.value.toString(),
                fontSize = 32.sp,
                fontFamily = customFont1,
                color = Color.White,
                modifier = Modifier
                    .offset(x = 50.dp, y = 200.dp)
                    .padding(0.dp))
            Canvas(modifier = Modifier.fillMaxSize(), contentDescription = "Line Drawing") {
                // Draw a line from (100, 100) to (400, 400)
                drawLine(
                    color = Color.Gray,
                    start = Offset(x = 0f, y = -550f),
                    end = Offset(x = 1080f, y = -550f),
                    strokeWidth = 5f
                )
            }


        }

        Card( colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(50.dp, 50.dp, 40.dp, 30.dp),
            modifier = Modifier
                .size(500.dp)
                .offset(y = -90.dp)
        ) {
            Button(onClick = { pageNum.value=4
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        Client.postTask(
                            postTask(userName.value,"wash dishes",100)
                        )
                        // Handle success
                    } catch (e: Exception) {
                        // Handle error
                        println("error: ${e.message}")
                    }
                }},
                colors = ButtonDefaults.buttonColors(containerColor =Color.White),
                modifier = Modifier
                    .offset(290.dp, 10.dp)
                    .size(height = 80.dp, width = 80.dp)) {

            }
            Image(painter = add, contentDescription = null,
                Modifier
                    .size(50.dp)
                    .offset(305.dp, -55.dp))
            Box(contentAlignment = Alignment.Center){
                Text(
                    task.value,
                    modifier=Modifier.offset(50.dp,-80.dp),
                    fontFamily = customFont1,
                    fontSize = 18.sp)

            }
        }
    }
}