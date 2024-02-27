package com.example.testing.Repositories

import com.example.testing.Models.Person

interface PersonRepository {
    suspend fun addPerson(person: Person)
    suspend fun getAll() :MutableList<Person>
    suspend fun updatePerson(person: Person)
    suspend fun deletePerson(person: Person)
    suspend fun deleteAll()
}

class PersonRepositoryImpl : PersonRepository {

/*    val client = MongoClient.create()
    val database = client.getDatabase("UCL")
    val collection = database.getCollection<Person>("Person")*/

    override suspend fun addPerson(person: Person) {
      /*  collection.insertOne(person).also {
        }*/
    }

    override suspend fun getAll() : MutableList<Person> {
        return mutableListOf(
            Person("Mohamed",24,"Student"),
            Person("Al",23, "Student")
        )
    }

    override suspend fun updatePerson(person: Person) {

    }

    override suspend fun deletePerson(person: Person) {

    }

    override suspend fun deleteAll() {

    }
}