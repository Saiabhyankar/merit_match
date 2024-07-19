package com.example.meritmatch
data class UserDetails(
    val userName:String,
    val passWord:String
)

data class createResponse(
    val message:String
)

data class checkIfUserExists(
        val getRequest:String
        )

