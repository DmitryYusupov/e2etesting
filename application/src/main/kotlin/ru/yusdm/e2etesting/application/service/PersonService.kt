package ru.yusdm.e2etesting.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.yusdm.e2etesting.application.domain.Person
import ru.yusdm.e2etesting.application.repository.PersonRepository

@Service
@Transactional
class PersonService(private val personRepository: PersonRepository) {

    fun save(person: Person)  = personRepository.save(person)

    fun getById(id: Long): Person = personRepository.getById(id)

}