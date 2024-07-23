package com.example.meritmatch

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.meritmatch.ui.theme.TaskComposables
import kotlinx.coroutines.delay

@Composable

fun GetResponse(){
    val cursor:ApiService= viewModel()

    val getValue by cursor.data

    val check by cursor.pass

    val karmaPoints by cursor.karma

    val task by cursor.tasks
    cursor.interact()

    getUserName(checkIfUserExists(getValue.getValue))
    if(password.value!=""){
       cursor.checkPassword()
    }
    getPasswd(checkValue(check.passwd))
    var i=0
    cursor.getKarma()

    getKarmaPoints(karmaValue(karmaPoints.karmaPoint))




}

fun getUserName(checkIfUserExists: checkIfUserExists){
    userNameFromBackend.value=checkIfUserExists.getRequest
}

fun getPasswd(checkValue: checkValue){
    passwd.value= checkValue.bool
}

fun getKarmaPoints(karmaValue: karmaValue){
    karmaPoint.value=karmaValue.karmaPoints
}


