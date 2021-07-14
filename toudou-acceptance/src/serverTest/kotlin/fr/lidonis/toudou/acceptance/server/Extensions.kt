package fr.lidonis.toudou.acceptance.server

import com.fasterxml.jackson.databind.JsonNode
import io.kotest.assertions.json.shouldContainJsonKey
import io.kotest.assertions.json.shouldContainJsonKeyValue
import org.http4k.core.Body
import org.http4k.core.Request


fun Request.body(type: JsonNode) = body(type.toString())
fun Body.shouldContainJsonKey(path: String) = this.toString().shouldContainJsonKey(path)
fun Body.shouldContainJsonKeyValue(path: String, value: String) =
    this.toString().shouldContainJsonKeyValue(path, value)