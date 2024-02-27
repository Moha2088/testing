package com.example.testing.ViewModels

import androidx.lifecycle.ViewModel
import com.example.testing.Models.Person
import com.example.testing.Repositories.PersonRepositoryImpl
import kotlinx.coroutines.runBlocking

class PersonViewModel (val personRepo:PersonRepositoryImpl) : ViewModel(){
    init {
        getModelData()
    }


    val personList: MutableList<Person> = mutableListOf()


    fun add(person: Person) {
        runBlocking {
            personRepo.addPerson(person)
        }
    }

    private fun getModelData() {
        runBlocking {
            personRepo.getAll().forEach {
                personList.add(it)
            }
        }
    }
}