package ru.yusdm.e2etesting.simple.util

import org.springframework.context.ApplicationContext

inline fun <reified T> ApplicationContext.getTypedBean(): T {
    return this.getBean(T::class.java)
}