package com.example.meritmatch

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable

fun RequestsSent() {

    val cursor: ApiService = viewModel()
    val accept by cursor.tasksAccept



    cursor.acceptTasks()
    acceptTask(accept.tasks)
    }



@Composable
fun getDetails(task: tasks1) {
    val cursor: ApiService = viewModel()
    val context= LocalContext.current
    val check by cursor.Valid
    acceptKarmaPoints.value=task.karmapoints
    cursor.transactionCheck()
    checkTrans(validResponse(check.result))
    Column {
        Row {
            Card(modifier = Modifier
                .padding(10.dp)
                .size(height = 150.dp, width = 450.dp),
                colors = CardDefaults.cardColors(containerColor = Color(3, 0, 107, 255).copy(0.5f))) {
                Row {

                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(task.username.uppercase(),
                        modifier = Modifier.padding(start=10.dp,top = 15.dp),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(task.tasks.uppercase(),
                        modifier = Modifier.padding(start=10.dp,top = 15.dp),
                        fontSize = 18.sp)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(task.karmapoints.toString().uppercase(),
                        modifier = Modifier
                            .padding(start = 10.dp, top = 15.dp)
                            .offset(x = 50.dp),
                        fontSize=18.sp)
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(onClick = {

                        if(transactionCheck.value=="possible"){

                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    Client.transaction(
                                        transactionProcess(userName.value,task.username,task.karmapoints)
                                    )
                                    // Handle success
                                } catch (e: Exception) {
                                    // Handle error
                                    println("error: ${e.message}")
                                }
                            }

                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    Client.acceptedCheck(accept(task.id))

                                } catch (e: Exception) {

                                    println("error: ${e.message}")
                                }
                            }
                            cursor.acceptTasks()

                            Toast.makeText(context,"Transaction Success full",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(context,"Invalid Transaction",Toast.LENGTH_SHORT).show()
                        }


                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(17,4,36)),
                        modifier= Modifier.offset(x=-165.dp,y=75.dp)
                            .size(width=150.dp, height = 50.dp)) {
                        Text("Accept")
                    }
                }
            }
        }
    }
}

@Composable
fun acceptTask(tasks: List<tasks1>) {
    Box(contentAlignment = Alignment.Center,
        modifier=Modifier.offset(x=-100.dp,y=250.dp)) {
        Text("Tasks To Be Reserved",
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            modifier = Modifier.offset(x=100.dp,y=-600.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            Modifier
                .size(1000.dp)
                .offset(x = 100.dp, y = 0.dp)
        ) {
            items(tasks) { details ->
                getDetails(task = details)
            }
        }
    }
}
fun checkTrans(validResponse: validResponse){
   transactionCheck.value=validResponse.result
}