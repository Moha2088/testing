package com.example.testing.Repositories

interface Person {
    suspend fun addPerson(person: Person)
    suspend fun readPerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun deletePerson(person: Person)
}

class PersonRepository : Person {
    override suspend fun addPerson(person: Person) {
        TODO("Not yet implemented")
    }

    override suspend fun readPerson(person: Person) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePerson(person: Person) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePerson(person: Person) {
        TODO("Not yet implemented")
    }
}