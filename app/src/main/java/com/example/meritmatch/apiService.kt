package com.example.meritmatch

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApiService : ViewModel() {
    private val _data = mutableStateOf(GetReq1())
    val data: State<GetReq1> = _data

    private val _pass = mutableStateOf(PostReq1())
    val pass: State<PostReq1> = _pass

    private val _karma = mutableStateOf(GetReq2())
    val karma: State<GetReq2> = _karma


    fun interact() {
        viewModelScope.launch {

            val response = Client.checkIfUserExists(userName.value)
            try {
                _data.value = _data.value.copy(
                    getValue = response.getRequest
                )
            } catch (e: Exception) {
                println("error: ${e.message}")
            }

        }


    }

    fun checkPassword() {
        viewModelScope.launch {
            try {
                val response=Client.checkPassWord(
                    Password(userName.value, password.value)
                )
                _pass.value=_pass.value.copy(
                    passwd = response.bool
                )
                // Handle success
            } catch (e: Exception) {
                // Handle error
                println("error: ${e.message}")
            }
        }
    }

    fun getKarma() {
        viewModelScope.launch {
            try {
                val response=Client.getKarmaPoints(
                    (userName.value)
                )
                _karma.value=_karma.value.copy(
                    karmaPoint = response.karmaPoints
                )
                // Handle success
            } catch (e: Exception) {
                // Handle error
                println("error: ${e.message}")
            }
        }
    }

    data class GetReq1(
        val getValue: String = ""
    )

    data class PostReq1(
        val passwd:String=""
    )

    data class GetReq2(
        val karmaPoint:Int=0
    )
}


