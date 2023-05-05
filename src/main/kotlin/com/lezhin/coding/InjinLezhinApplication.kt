package com.lezhin.coding

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class InjinLezhinApplication

fun main(args: Array<String>) {
    runApplication<InjinLezhinApplication>(*args)
}
