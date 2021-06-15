package ru.yusdm.e2etesting.simple.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("application")
@ConstructorBinding
data class ApplicationConfig(val host: String, val port: Int)