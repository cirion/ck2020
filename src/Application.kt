package com.seberin.bday

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.webjars.*
import java.time.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.logging.*
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.request.receiveParameters

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

data class IndexData(val items: List<Int>)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Locations) {
    }

    install(DefaultHeaders) {
        header("X-Robots-Tag", "noindex, nofollow, noarchive, nosnippet, noimageindex")
    }

    install(Webjars) {
        path = "/webjars" //defaults to /webjars
        this.zone = ZoneId.systemDefault() //defaults to ZoneId.systemDefault()
    }

    val client = HttpClient(Apache) {
        install(Logging) {
            level = LogLevel.HEADERS
        }
    }

    routing {
        route("/login") {
            get {
                call.respond(FreeMarkerContent("login.ftl", null))
            }
            post {
                val post = call.receiveParameters()
                if (post["username"] != null && post["username"] == post["password"]) {
                    call.respondRedirect("/", permanent = false)
                } else {
                    call.respond(FreeMarkerContent("login.ftl", mapOf("error" to "I knew you were a robot from the past! That is, unless...")))
                }
            }
        }
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/html-freemarker") {
            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to IndexData(listOf(1, 42, 420))), ""))
        }

        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"HTML" }
                    ul {
                        for (n in 1..10) {
                            li { +"$n" }
                        }
                    }
                }
            }
        }

        get("/styles.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.red
                }
                p {
                    fontSize = 2.em
                }
                rule("p.myclass") {
                    color = Color.blue
                }
            }
        }

        // Static feature. Try to access `/static/ktor_logo.svg`
        static {
            staticBasePackage = "/static"

            resource("favicon.ico")
            resource("robots.txt")

            route("static") {
                files("resources/static")
            }
        }

        get<MyLocation> {
            call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
        }
        // Register nested routes
        get<Type.Edit> {
            call.respondText("Inside $it")
        }
        get<Type.List> {
            call.respondText("Inside $it")
        }

        get("/webjars") {
            call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
        }
    }
}

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}") data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}

fun FlowOrMetaDataContent.styleCss(builder: CSSBuilder.() -> Unit) {
    style(type = ContentType.Text.CSS.toString()) {
        +CSSBuilder().apply(builder).toString()
    }
}

fun CommonAttributeGroupFacade.style(builder: CSSBuilder.() -> Unit) {
    this.style = CSSBuilder().apply(builder).toString().trim()
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}
