package ru.yusdm.e2etesting.simple.service.tests

import org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns
import org.junit.platform.engine.discovery.DiscoverySelectors.selectClass
import org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage
import org.junit.platform.launcher.Launcher
import org.junit.platform.launcher.LauncherDiscoveryRequest
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder
import org.junit.platform.launcher.core.LauncherFactory
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import ru.yusdm.e2etesting.simple.PersonSaveTest
import java.time.LocalDateTime


@Service
class TestPersonSaveSchedulerService {

    private val listener = SummaryGeneratingListener()
    private var launcher: Launcher
    private var testRequest: LauncherDiscoveryRequest
    private val log = LoggerFactory.getLogger(TestPersonSaveSchedulerService::class.java)

    init {
        testRequest = LauncherDiscoveryRequestBuilder.request()
            .selectors(selectClass(PersonSaveTest::class.java))
            .build()

        launcher = LauncherFactory.create().apply {
            discover(testRequest)
            registerTestExecutionListeners(listener)
        }
    }

    fun test() {
        launcher.execute(testRequest)
        val summary = listener.summary
        if (summary.testsFailedCount > 0) {
            with(summary.failures[0]) {
                log.error(this.testIdentifier.displayName + ": " + exception)
            }
        }
    }

}

@Component
class TestPersonSaveScheduler(val testPersonSaveSchedulerService: TestPersonSaveSchedulerService) {

    @Scheduled(fixedDelay = 10_000, initialDelay = 3000)
    fun schedule() {
        println("In schedule " + LocalDateTime.now())
        testPersonSaveSchedulerService.test()
    }


}