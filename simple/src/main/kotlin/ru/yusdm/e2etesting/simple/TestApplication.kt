package ru.yusdm.e2etesting.simple

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TestApplication

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}
