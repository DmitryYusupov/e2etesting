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

@SpringBootTest
@EnableConfigurationProperties(ApplicationConfig::class)
@Transactional
class PersonSaveTest {

    @Autowired
    private lateinit var applicationConfig: ApplicationConfig

    @Autowired
    private lateinit var personService: PersonService

    @Test
    fun testPersonSave() {
        val restTemplate = RestTemplate()
        val person = Person(1, "test")
        restTemplate.postForEntity(
            "http://${applicationConfig.host}:${applicationConfig.port}/api/person",
            person,
            Unit::class.java
        )

        val factPerson = personService.getById(1)
        Assertions.assertEquals(factPerson.id, 1)
        Assertions.assertEquals(factPerson.name, "test")
    }

}