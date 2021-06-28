package ru.yusdm.e2etesting.simple.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties("application")
@ConstructorBinding
data class ApplicationProps(val host: String, val port: Int)

@Configuration
@EnableConfigurationProperties(ApplicationProps::class)
class ApplicationPropsConfig(val applicationProps: ApplicationProps)