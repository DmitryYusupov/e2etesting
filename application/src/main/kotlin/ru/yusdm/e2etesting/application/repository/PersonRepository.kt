package ru.yusdm.e2etesting.application.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.yusdm.e2etesting.application.domain.Person
import java.util.concurrent.ConcurrentHashMap

interface PersonRepository: JpaRepository<Person, Long>