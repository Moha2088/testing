package com.example.testing.models

import java.util.Date

data class Person(
    val name: String,
    val age: Int,
    val occupation: String,
    val creationDate:String = Date().toString().substring(0,19)
)
 {

}