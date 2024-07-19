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
data class Password(
    var userName: String,
    var passWord:String
)

data class checkValue(
    var bool:String
)

data class karma(
    var username:String
)

data class karmaValue(
    var karmaPoints:Int
)
