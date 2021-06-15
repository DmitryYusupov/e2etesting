package ru.yusdm.e2etesting.simple.repository

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import ru.yusdm.e2etesting.simple.model.Person

@Repository
class PersonRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) {

    fun getById(id: Long): Person {
        val sql = "SELECT * FROM person WHERE id = :id"

        return jdbcTemplate.queryForObject(
            sql, MapSqlParameterSource("id", id)
        ) { rs,_ -> Person(rs.getLong("id"), rs.getString("name")) }!!
    }

    fun deleteById(id: Long) {
        jdbcTemplate.update(
            "DELETE FROM person WHERE id = :id",
            MapSqlParameterSource("id", id)
        )
    }
}