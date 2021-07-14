package fr.lidonis.toudou.server

import fr.lidonis.toudou.Label
import fr.lidonis.toudou.Toudous
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        gson()
    }
    routing {
        get("/") {
            call.respond(Toudous().all)
        }
        post("/") {
            val newToudouRequest = call.receive<NewToudouRequest>()
            val toudou = Toudous().add(Label(newToudouRequest.label))
            call.respond(toudou)
        }
    }
}

data class NewToudouRequest(val label: String)