package ru.yusdm.e2etesting.simple.service.tests

import org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns
import org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage
import org.junit.platform.launcher.Launcher
import org.junit.platform.launcher.LauncherDiscoveryRequest
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder
import org.junit.platform.launcher.core.LauncherFactory
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


@Service
class TestPersonSaveSchedulerService {

    private val listener = SummaryGeneratingListener()
    private lateinit var launcher: Launcher
    private lateinit var testRequest: LauncherDiscoveryRequest

    init {
        val testRequest = LauncherDiscoveryRequestBuilder.request()
            .selectors(selectPackage("ru.yusdm.e2etesting.simple"))
            .filters(includeClassNamePatterns(".*Test"))
            .build()

        launcher = LauncherFactory.create().apply {
            discover(testRequest)
            registerTestExecutionListeners(listener)
        }
    }

    fun test() {
        launcher.execute(testRequest)
    }

}

@Component
class TestPersonSaveScheduler(val testPersonSaveSchedulerService: TestPersonSaveSchedulerService) {

    @Scheduled(fixedDelay = 3000)
    fun schedule() {
      //  testPersonSaveSchedulerService.test()
    }


}