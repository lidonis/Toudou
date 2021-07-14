package fr.lidonis.toudou.server


import fr.lidonis.toudou.Label
import fr.lidonis.toudou.Toudou
import fr.lidonis.toudou.Toudous
import org.http4k.client.ApacheClient
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.filter.ServerFilters
import org.http4k.format.Jackson.auto
import org.http4k.lens.BiDiBodyLens
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

val listToudousResponse: BiDiBodyLens<List<Toudou>> = Body.auto<List<Toudou>>().toLens()
val toudouResponse: BiDiBodyLens<Toudou> = Body.auto<Toudou>().toLens()

val toudouApp: HttpHandler = ServerFilters.CatchLensFailure.then(
    routes(
        "/" bind GET to allToudous(),
        "/" bind POST to addToudou()
    )
)

private fun allToudous(): (Request) -> Response =
    { listToudousResponse.inject(Toudous().all, Response(Status.OK)) }

data class NewToudouRequest(val label: String)

private fun addToudou(): (Request) -> Response {
    val newToudouRequest = Body.auto<NewToudouRequest>().toLens()

    return { request: Request ->
        toudouResponse.inject(
            Toudous().add(Label(newToudouRequest(request).label)),
            Response(Status.OK)
        )
    }
}

fun main() {
    val server = toudouApp.asServer(Undertow(9000)).start()

    val client = ApacheClient()

    val request = Request(GET, "http://localhost:9000").query("name", "John Doe")

    println(client(request))

    server.stop()
}