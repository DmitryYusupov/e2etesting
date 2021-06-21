package ru.yusdm.e2etesting.simple

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import ru.yusdm.e2etesting.simple.config.ApplicationConfig
import ru.yusdm.e2etesting.simple.model.Person
import ru.yusdm.e2etesting.simple.service.PersonService

@SpringBootTest(
    properties = [
        "spring.profiles.active=test",
        "spring.application.admin.enabled=true",
        "spring.application.admin.jmx-name=org.springframework.boot:type=Admin3,name=SpringApplication3"
    ],
)
@EnableConfigurationProperties(ApplicationConfig::class)
@Transactional
class PersonSaveTest {

    @Autowired
    private lateinit var applicationConfig: ApplicationConfig

    @Autowired
    private lateinit var personService: PersonService

    @Test
    fun testPersonSave() {
        println("Test begin")
        val restTemplate = RestTemplate()
        val personId = 1L
        val person = Person(personId, "test")
        restTemplate.postForEntity(
            "http://${applicationConfig.host}:${applicationConfig.port}/api/person",
            person,
            Unit::class.java
        )

        println("get by id")
        val factPerson = personService.getById(personId)
        val count = personService.deleteById(personId)
        println("deleted by id $count")
        Assertions.assertEquals(factPerson.id, 1L)
        Assertions.assertEquals(factPerson.name, "test")
    }

}