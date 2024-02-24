package com.example.testing.ViewModels

import com.example.testing.Repositories.PersonRepositoryImpl
import com.example.testing.models.Person
import kotlinx.coroutines.runBlocking

class PersonViewModel(val personRepo: PersonRepositoryImpl) {
    init {
        getModelData()
    }

    val personList: MutableList<Person> = mutableListOf()


    fun add(person: Person) {
        if (personList.size < 5) {
            runBlocking {
                personRepo.addPerson(person)
            }
        }

        else throw IllegalStateException("No more than 5 users can be added to the list")
    }

    fun getModelData() {
        runBlocking {
            personRepo.getAll().forEach {
                personList.add(it)
            }
        }
    }
}