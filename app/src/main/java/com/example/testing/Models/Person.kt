package com.example.testing.Models

import java.util.Date

data class Person(
    val name: String,
    val age: Int,
    val occupation: String,
    val creationDate:String = Date().toString().substring(0,19)
)
 {

}