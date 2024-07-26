package com.example.meritmatch



import android.R.attr.x
import android.R.attr.y
import android.R.color
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meritmatch.ui.theme.TaskComposables
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun Transaction(navigate:()->Unit){
    val signOut= painterResource(id = R.drawable.signout)
    val context= LocalContext.current
    val cursor:ApiService= viewModel()
    val task1 by cursor.tasks
    val accept by cursor.tasksAccept
    cursor.getTasks()
    cursor.acceptTasks()
    cursor.getKarma()
    val karmaPoints by cursor.karma
    if(! task1.loading)
        getTask( task1.tasks)
    val customFont= FontFamily(
         Font( R.font.generica)
    )
    val customFont1= FontFamily(
        Font( R.font.coverface)
    )
    val date =LocalDate.now()
    val time=LocalTime.now()
    val add = painterResource(id = R.drawable.add)
    val requests= painterResource(id = R.drawable.img)
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(53, 0, 107, 255)),
            shape = RoundedCornerShape(0.dp, 0.dp, 40.dp, 30.dp),
            modifier = Modifier.size(500.dp)
        ) {
            Button(onClick = {
                userName.value=""
                password.value=""
                pageNum.value=1
            navigate()},
                modifier = Modifier.offset(x=300.dp,y=100.dp)) {
                Text("Sign Out")
            }
            if (time.hour in 6..12) {
                Text(
                    "Good Morning ,",
                    modifier = Modifier.offset(50.dp, 50.dp),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.ExtraBold
                )
            } else if (time.hour in 12..18) {
                Text(
                    "Good Afternoon ,",
                    modifier = Modifier.offset(50.dp, 50.dp),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.ExtraBold
                )
            } else {
                Text(
                    "Good Evening ,",
                    modifier = Modifier.offset(50.dp, 50.dp),
                    color = Color.White,
                    fontSize = 28.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Text(userName.value,
                fontSize = 28.sp,
                fontFamily = customFont,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier
                    .offset(x = 50.dp, y = 55.dp)
                    .padding(0.dp))
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = date.dayOfMonth.toString() + " " + date.month.toString() + " " + date.year.toString(),
                modifier = Modifier.offset(50.dp, 50.dp),
                color = Color.Gray,
                fontSize = 20.sp
            )

            Text(
                "Total Karma Points",
                modifier = Modifier
                    .offset(50.dp, 130.dp)
                    .padding(0.dp),
                color = Color.Gray,
                fontSize = 20.sp
            )
            Text(
                karmaPoints.karmaPoint.toString(),
                fontSize = 32.sp,
                fontFamily = customFont1,
                color = Color.White,
                modifier = Modifier
                    .offset(x = 50.dp, y = 150.dp)
                    .padding(0.dp)
            )
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(50.dp, 50.dp, 40.dp, 30.dp),
            modifier = Modifier
                .size(500.dp)
                .offset(y = -90.dp)
        ) {
            Button(
                onClick = {
                    pageNum.value=3
                    navigate()

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .offset(290.dp, 10.dp)
                    .size(height = 80.dp, width = 80.dp)
            ) {

            }
            Image(
                painter = add, contentDescription = null,
                Modifier
                    .size(50.dp)
                    .offset(305.dp, -55.dp)
            )

            Button(
                onClick = {
                    if(accept.tasks.isEmpty())
                        Toast.makeText(context,"No task to be approved",Toast.LENGTH_SHORT).show()
                    else {
                        pageNum.value = 4
                        navigate()
                    }

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .offset(200.dp, -110.dp)
                    .size(height = 80.dp, width = 80.dp)
            ) {

            }
            Image(
                painter = requests, contentDescription = null,
                Modifier
                    .size(60.dp)
                    .offset(215.dp, -185.dp)
            )
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.offset(y=0.dp)) {
                Column (){
                    Text(
                        "Available Tasks",
                        modifier = Modifier.offset(50.dp, -180.dp),
                        fontFamily = customFont1,
                        fontSize = 18.sp

                    )
                    if (!task1.loading)
                      getTask(tasks = task1.tasks)

                }
            }
        }
    }
}

@Composable
fun retrieveDetails(task: tasks) {
    val cursor:ApiService= viewModel()

    val reserve= painterResource(id = R.drawable.reserve)
    Column {
        Row {
            Card(modifier = Modifier
                .padding(10.dp)
                .size(height = 150.dp, width = 450.dp)
                .clickable { check.value = 1 },
                colors = CardDefaults.cardColors(containerColor = Color(3, 0, 107, 255).copy(0.5f))) {
                Row {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(task.username.uppercase(),
                        modifier = Modifier.padding(start=10.dp,top = 15.dp),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(task.tasks.uppercase(),
                        modifier = Modifier.padding(start=10.dp,top = 15.dp),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(task.karmapoints.toString().uppercase(),
                        modifier = Modifier.padding(start=10.dp,top = 15.dp),
                        fontSize=18.sp)
                    Spacer(modifier = Modifier.padding(8.dp))

                }
            }
            if(check.value==1){
                AlertDialog(onDismissRequest = { check. value=2}, confirmButton = {  },
                    text={
                        Column(){
                            Text("Do you Want to reserve the task",
                                modifier = Modifier.offset(x=20.dp),
                                fontSize=18.sp)
                            Button(onClick = {
                                check.value=2
                                taskIdentity.value=task.id
                                CoroutineScope(Dispatchers.IO).launch {
                                    try {
                                        Client.reserveTask(
                                            karma(taskIdentity.value, userName.value)
                                        )
                                        // Handle success
                                    } catch (e: Exception) {
                                        // Handle error
                                        println("error: ${e.message}")
                                    }
                                }
                                cursor.getTasks()

                            },
                                modifier=Modifier.offset(x=80.dp,y=45.dp),
                                colors = ButtonDefaults.buttonColors(Color.White)) {

                            }

                            Image(painter = reserve, contentDescription = null,
                                modifier= Modifier
                                    .size(60.dp)
                                    .offset(x = 80.dp))
                        }

                    })
            }
        }
    }

}

@Composable
fun getTask(tasks: List<tasks>) {
    LazyVerticalGrid(columns = GridCells.Fixed(1),
        Modifier
            .size(10000.dp)
            .offset(y = -100.dp)) {
        items(tasks) { details ->
            retrieveDetails(task = details)
        }
    }
}