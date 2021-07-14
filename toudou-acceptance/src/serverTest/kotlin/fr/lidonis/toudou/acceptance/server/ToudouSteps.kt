package fr.lidonis.toudou.acceptance.server

import fr.lidonis.toudou.server.toudouApp
import io.cucumber.java8.En
import io.kotest.assertions.assertSoftly
import org.http4k.core.ContentType
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson
import org.http4k.kotest.shouldHaveBody
import org.http4k.kotest.shouldHaveHeader
import org.http4k.kotest.shouldHaveStatus

val json = Jackson

class ToudouSteps : En {

    private lateinit var response: Response

    init {
        Given("Empty/A toudous") {

        }

        When("I list all toudous") {
            response = toudouApp(Request(Method.GET, "/"))
        }
        When("I add a toudou with label {string}") { label: String ->
            response = toudouApp(Request(Method.POST, "/").body(json {
                obj(
                    "label" to string(label)
                )
            }))
        }

        Then("my toudous are empty") {
            assertSoftly(response) {
                shouldHaveStatus(OK)
                shouldHaveHeader("content-type", ContentType.APPLICATION_JSON.toHeaderValue())
                shouldHaveBody("[]")
            }
        }
        Then("a toudou should be created") {
            assertSoftly(response) {
                shouldHaveStatus(OK)
                shouldHaveHeader("content-type", ContentType.APPLICATION_JSON.toHeaderValue())
            }
        }

        Then("the toudou should have an Id") {
            response.body.shouldContainJsonKey("toudouId")
        }

        Then("the toudou should have a label {string}") { label: String ->
            response.body.shouldContainJsonKeyValue("$.label", label)
        }

    }
}

