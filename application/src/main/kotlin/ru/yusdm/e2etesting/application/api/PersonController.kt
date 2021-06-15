package ru.yusdm.e2etesting.application.api

import org.springframework.web.bind.annotation.*
import ru.yusdm.e2etesting.application.domain.Person
import ru.yusdm.e2etesting.application.service.PersonService

@RestController
@RequestMapping(value = ["/api/person"])
class PersonController(val personService: PersonService) {

    @PostMapping
    fun save(@RequestBody person: Person) {
        personService.save(person)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): Person {
        return personService.getById(id)
    }

}