package fr.lidonis.toudou.acceptance.domain

import fr.lidonis.toudou.Label
import fr.lidonis.toudou.Toudou
import fr.lidonis.toudou.Toudous
import io.cucumber.java8.En
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.string.beEmpty

class ToudouSteps : En {

    private lateinit var toudous: Toudous
    private lateinit var toudouList: List<Toudou>
    private lateinit var toudou: Toudou

    init {
        Given("Empty/A toudous") {
            toudous = Toudous()
        }

        When("I list all toudous") {
            toudouList = toudous.all
        }
        When("I add a toudou with label {string}") { label: String ->
            toudou = toudous.add(Label(label))
        }

        Then("my toudous are empty") {
            toudouList.shouldBeEmpty()
        }

        Then("a toudou should be created") {}

        Then("the toudou should have an Id") {
            toudou.toudouId.toString() shouldNot beEmpty()
        }

        Then("the toudou should have a label {string}") { label: String ->
            toudou.label.value shouldBe label
        }
    }
}