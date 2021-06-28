package ru.yusdm.e2etesting.simple.service.tests.common

import org.junit.platform.engine.discovery.DiscoverySelectors.selectClass
import org.junit.platform.launcher.Launcher
import org.junit.platform.launcher.LauncherDiscoveryRequest
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder
import org.junit.platform.launcher.core.LauncherFactory
import org.junit.platform.launcher.listeners.SummaryGeneratingListener
import org.junit.platform.launcher.listeners.TestExecutionSummary


abstract class TestRunner(clazz: Class<*>) {

    private val listener = SummaryGeneratingListener()
    private var launcher: Launcher
    private var testRequest: LauncherDiscoveryRequest =
        LauncherDiscoveryRequestBuilder.request()
            .selectors(selectClass(clazz))
            .build()

    init {
        launcher = LauncherFactory.create().apply {
            discover(testRequest)
            registerTestExecutionListeners(listener)
        }
    }

    fun testAll() {
        launcher.execute(testRequest)
        val summary = listener.summary
        if (summary.testsFailedCount > 0) {
            onTestFail(summary)
        }
    }

    abstract fun onTestFail(testExecutionSummary: TestExecutionSummary)
}
