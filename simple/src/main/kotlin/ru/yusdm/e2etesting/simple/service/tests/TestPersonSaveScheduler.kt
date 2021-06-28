package ru.yusdm.e2etesting.simple.service.tests

import org.junit.platform.launcher.listeners.TestExecutionSummary
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import ru.yusdm.e2etesting.simple.service.tests.common.TestRunner
import java.time.LocalDateTime

@Service
class TestPersonSaveSchedulerService: TestRunner(PersonSaveTest::class.java) {

    private val log = LoggerFactory.getLogger(TestPersonSaveSchedulerService::class.java)

    override fun onTestFail(testExecutionSummary: TestExecutionSummary) {
        with(testExecutionSummary.failures[0]) {
            log.error(this.testIdentifier.displayName + ": " + exception)
        }
    }

}

@Component
class TestPersonSaveScheduler(val testPersonSaveSchedulerService: TestPersonSaveSchedulerService) {

    @Scheduled(fixedDelay = 10_000, initialDelay = 3000)
    fun schedule() {
        println("In schedule " + LocalDateTime.now())
        testPersonSaveSchedulerService.testAll()
    }


}