package ru.yusdm.e2etesting.simple.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.e2etesting.simple.model.Person
import ru.yusdm.e2etesting.simple.repository.PersonRepository

@Service
@Transactional
class PersonService(private val personRepository: PersonRepository) {

    fun getById(id: Long): Person {
        return personRepository.getById(id)
    }

    fun deleteById(id: Long): Int {
        return personRepository.deleteById(id)
    }
}