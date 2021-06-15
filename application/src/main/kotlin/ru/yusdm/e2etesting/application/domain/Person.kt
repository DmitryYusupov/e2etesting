package ru.yusdm.e2etesting.application.domain

import javax.persistence.*

const val DEFAULT_ID = 0L

@Entity
@Table(name = "person")
class Person(
    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    var id: Long = DEFAULT_ID,

    @Column(name = "name", nullable = false)
    val name: String
)