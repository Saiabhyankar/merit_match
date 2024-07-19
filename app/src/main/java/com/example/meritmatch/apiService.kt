package com.example.meritmatch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApiService : ViewModel(){
    private val _data= mutableStateOf(GetReq1())
    val data: State<GetReq1> = _data


    init{
        interact()
    }
    private fun interact(){
        viewModelScope.launch {
            val response= Client.checkIfUserExists(userName.value)
            try {
                _data.value = _data.value.copy(
                    getValue = response.getRequest
                )
            }catch (e:Exception){
                println("error: ${e.message}")
            }

        }
    }

    data class GetReq1(
            val getValue:String=""
            )


}