package ru.yusdm.e2etesting.simple.service.tests

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.web.client.RestTemplate
import ru.yusdm.e2etesting.simple.ApplicationContextHolder
import ru.yusdm.e2etesting.simple.config.ApplicationProps
import ru.yusdm.e2etesting.simple.config.ApplicationPropsConfig
import ru.yusdm.e2etesting.simple.model.Person
import ru.yusdm.e2etesting.simple.service.PersonService
import ru.yusdm.e2etesting.simple.util.getTypedBean

open class PersonSaveTest: ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext

    private val applicationProps: ApplicationProps by lazy { getBean<ApplicationPropsConfig>().applicationProps }

    private val personService: PersonService by lazy { getBean() }

    @Test
    fun testPersonSave() {
        println("Test begin")
        val restTemplate = RestTemplate()
        val personId = 1L
        val person = Person(personId, "test")
        restTemplate.postForEntity(
            "http://${applicationProps.host}:${applicationProps.port}/api/person",
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

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    private inline fun <reified T> getBean(): T {
        if (ApplicationContextHolder.context != null) {
            applicationContext = ApplicationContextHolder.context!!
        }

        return applicationContext.getTypedBean()
    }
}