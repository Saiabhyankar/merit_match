package com.example.meritmatch

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Box(Modifier.offset(x=-100.dp,y=350.dp)) {
            Text("Si")

            if (!accept.loading) {
                if(accept.tasks.isNotEmpty())
                    acceptTask(accept.tasks)
                else{
                    Text("No Tasks Available ")
                }
            }
        }


    }


@Composable
fun getDetails(task: tasks1) {
    Column {
        Row {
            Card(modifier = Modifier
                .padding(10.dp)
                .size(height = 60.dp, width = 450.dp),
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
                    Button(onClick = {
//                        CoroutineScope(Dispatchers.IO).launch {
//                            try {
//                                Client.reserveTask(
//                                    karma(userName.value)
//                                )
//                                // Handle success
//                            } catch (e: Exception) {
//                                // Handle error
//                                println("error: ${e.message}")
//                            }
//                        }
                    },
                        modifier= Modifier.offset(y=5.dp)) {
                        Text("Accept")
                    }
                }
            }
        }
    }
}

@Composable
fun acceptTask(tasks: List<tasks1>) {
    LazyVerticalGrid(columns = GridCells.Fixed(1),
        Modifier
            .size(1000.dp)
            .offset(x=100.dp,y = 0.dp)) {
        items(tasks) { details ->
            getDetails(task = details)
        }
    }
}