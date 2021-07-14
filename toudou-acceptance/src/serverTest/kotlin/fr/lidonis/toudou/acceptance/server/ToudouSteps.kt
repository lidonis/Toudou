package fr.lidonis.toudou.acceptance.serverktor

import fr.lidonis.toudou.server.module
import io.cucumber.java8.En
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.json.shouldContainJsonKey
import io.kotest.assertions.json.shouldContainJsonKeyValue
import io.kotest.assertions.ktor.shouldHaveContent
import io.kotest.assertions.ktor.shouldHaveHeader
import io.kotest.assertions.ktor.shouldHaveStatus
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.testing.*
import java.nio.charset.StandardCharsets


class ToudouSteps : En {

    private lateinit var response: TestApplicationResponse

    init {
        Given("Empty/A toudous") {

        }

        When("I list all toudous") {
            withTestApplication(Application::module) {
                response = handleRequest(Get, "/").response
            }
        }
        When("I add a toudou with label {string}") { label: String ->
            withTestApplication(Application::module) {
                response = handleRequest(Post, "/") {
                    addHeader(HttpHeaders.ContentType, Json.toString())
                    setBody("{ \"label\": \"${label}\" }")
                }.response
            }
        }

        Then("my toudous are empty") {
            assertSoftly(response) {
                shouldHaveStatus(OK)
                shouldHaveHeader(
                    HttpHeaders.ContentType,
                    Json.withCharset(StandardCharsets.UTF_8).toString()
                )
                shouldHaveContent("[]")
            }
        }
        Then("a toudou should be created") {
            assertSoftly(response) {
                shouldHaveStatus(OK)
                shouldHaveHeader(
                    HttpHeaders.ContentType,
                    Json.withCharset(StandardCharsets.UTF_8).toString()
                )
            }
        }

        Then("the toudou should have an Id") {
            response.content.shouldContainJsonKey("toudouId")
        }

        Then("the toudou should have a label {string}") { label: String ->
            response.content.shouldContainJsonKeyValue("label", label)
        }

    }
}

