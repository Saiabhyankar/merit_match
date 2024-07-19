package com.example.meritmatch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable

fun GetResponse(){
    val cursor:ApiService= viewModel()

    val getValue by cursor.data



    getUserName(checkIfUserExists(getValue.getValue))
}

fun getUserName(checkIfUserExists: checkIfUserExists){
    userNameFromBackend.value=checkIfUserExists.getRequest
}

