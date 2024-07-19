package com.example.meritmatch



import android.R.attr.x
import android.R.attr.y
import android.R.color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun Transaction(){
    val customFont= FontFamily(
         Font( R.font.generica)
    )
    val date =LocalDate.now()
    val time=LocalTime.now()
    Column {
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
                fontSize = 18.sp)
            Text("Total Karma Points",
                modifier=Modifier.offset(50.dp,250.dp),
                color=Color.Gray,
                fontSize = 18.sp)



        }
        Canvas(modifier = Modifier.fillMaxSize(), contentDescription = "Line Drawing") {
            // Draw a line from (100, 100) to (400, 400)
            drawLine(
                color = Color.Gray,
                start = Offset(x = 0f, y = -550f),
                end = Offset(x = 1080f, y = -550f),
                strokeWidth = 5f
            )
        }

        Card( colors = CardDefaults.cardColors(containerColor = Color(254,255,254,255)),
            shape = RoundedCornerShape(50.dp, 50.dp, 40.dp, 30.dp),
            modifier = Modifier
                .fillMaxSize()
                .offset(y = -130.dp)
        ) {

        }
    }


}