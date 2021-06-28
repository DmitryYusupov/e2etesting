package ru.yusdm.e2etesting.simple

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.scheduling.annotation.EnableScheduling

object ApplicationContextHolder {
	var context: ApplicationContext? = null
}

@SpringBootApplication
@EnableScheduling
class TestApplication: ApplicationContextAware {

	override fun setApplicationContext(applicationContext: ApplicationContext) {
		ApplicationContextHolder.context = applicationContext
	}

}

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}
