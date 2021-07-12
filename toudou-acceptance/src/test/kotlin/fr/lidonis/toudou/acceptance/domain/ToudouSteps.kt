package fr.lidonis.toudou.acceptance.domain

import fr.lidonis.toudou.Toudou
import fr.lidonis.toudou.Toudous
import io.cucumber.java8.En
import io.kotest.matchers.collections.shouldBeEmpty

class ToudouSteps : En {

    private lateinit var toudous: Toudous
    private lateinit var toudouList: List<Toudou>

    init {
        Given("Empty Toudous") {
            toudous = Toudous()
        }
        When("I list all toudous") {
            toudouList = toudous.list()
        }
        Then("my toudous are empty") {
            toudouList.shouldBeEmpty()
        }

    }
}