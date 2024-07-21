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

data class postTask(
    var username:String,
    var task:String,
    var karmapoints:Int,
    var taskDescription:String
)

data class tasks(
        var karmapoints:Int,
        var tasks:String,
        var username:String
)

data class returnTasks(
    var tasks:List<tasks>
)

data class reserve(
    var update:String
)

data class tasks1(
    var karmapoints:Int,
    var tasks:String,
    var username:String
)

data class returnTasks1(
    var tasks:List<tasks1>
)
