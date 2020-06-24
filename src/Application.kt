package com.seberin.bday

// Welcome to Jank City, please enjoy your stay!

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

// Hopefully this goes without saying, but... don't ever do this in a real app. Any of this.

data class IndexData(val items: List<Int>)

data class Hasbro(
        val index: Int,
        val p1: String,
        val p2: String? = null,
        val p3: String? = null,
        val label: String? = null
)

private val Hasbro1 = Hasbro(
        index = 1,
        p1 = "ATTENTION",
        p2 = "Our automated intellectual property AI scanner has determined that this site contains NUMEROUS VIOLATIONS of our INTELLECTUAL PROPERTY and TRADEMARKS. Specifically, it contains FLAGRANT UNAPPROVED USAGE of DONATELLO who is a REGISTERED TRADEMARK of HASBRO throughout the ENTIRE UNIVERSE.\n",
        label = "Oh, no!"
)

private val Hasbro2 = Hasbro(
        index = 2,
        p1 = "Yes.",
        label = "What should I do?"
)

private val Hasbro3 = Hasbro(
        index = 3,
        p1 = "Please remove all unapproved usages of our INTELLECTUAL PROPERTY from your memory. You may access these mental images once again after legally purchasing one of our many fine products featuring the visage of DONATELLO.",
        p2 = "Furthermore, you must under no circumstanced visit <a href='https://discord.gg/PY4NC7e'>this suspicious Discord server</a>. Our automated intellectual property AI scanner has determined that it contains further VIOLATIONS of our INTELLECTUAL PROPERTY and TRADEMARKS.",
        p3 = "END COMMUNICATION"
)

data class Zune(
        val index: Int,
        val p1: String,
        val label: String
)

private val Zune1 = Zune(
        index = 1,
        p1 = "Bingo! We’ve got him now. Hang on…",
        label = "All right."
)

private val Zune2 = Zune(
        index = 2,
        p1 = "Drat! We just missed him. He did leave something behind, though: A Zune player!",
        label = "Seriously?"
)

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
        header("X-Engine", "Ktor") // will send this header with each response
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
        // TODO: Move to root, replace "/".
        route("/login") {
            get {
                call.respond(FreeMarkerContent("login.ftl", null))
            }
            post {
                val post = call.receiveParameters()
                val answer = post["answer"]?.toLowerCase()
                // TODO: Update with current headlines at https://www.startribune.com/eedition/login/
                // and https://www.startribune.com/
                if (answer?.startsWith("lt. bob kroll") == true
                        || answer?.startsWith("post-protest virus") == true
                ) {
                    call.respondRedirect("/mellon", permanent = false)
                } else {
                    call.respond(FreeMarkerContent("login.ftl", mapOf("error" to "*Gasp!* You <em>are</em> a robot from the past! Only a human in the present could answer this question.")))
                }
            }
        }

        route("/mellon") {
            get {
                call.respond(FreeMarkerContent("2_got_minneapolis.ftl", null))
            }
            post {
                call.respondRedirect("/knockknock", permanent = false)
            }
        }

        route("/knockknock") {
            get {
                call.respond(FreeMarkerContent("3_hasbro.ftl", Hasbro1))
            }
            post {
                val post = call.receiveParameters()
                val model = when (post["index"]) {
                    "1" -> Hasbro2
                    else -> Hasbro3
                }
                call.respond(FreeMarkerContent("3_hasbro.ftl", model))
            }
        }

        route("/carmensandiego") {
            get {
                call.respond(FreeMarkerContent("4_hiking.ftl", Hasbro1))
            }
            post {
                val post = call.receiveParameters()
                if (post["answer"]?.toLowerCase() == "new zealand") {
                    call.respondRedirect("/tramp", permanent = false)
                } else {
                    call.respond(FreeMarkerContent("4_hiking.ftl", mapOf("error" to "Hang on, let me check!<br /><br />Nope, he isn't there.")))
                }
            }
        }

        route("/tramp") {
            get {
                call.respond(FreeMarkerContent("5_city.ftl", Hasbro1))
            }
            post {
                val post = call.receiveParameters()
                if (post["answer"]?.toLowerCase() == "glenorchy"
                        || post["answer"]?.toLowerCase() == "kinloch") {
                    call.respondRedirect("/", permanent = false)
                } else if (post["answer"]?.toLowerCase() == "queenstown") {
                    call.respond(FreeMarkerContent("5_city.ftl", mapOf("error" to "That city is too large. Well, by New Zealand standards, at least. Interpol would have no trouble finding him there. We need to find some place smaller and closer to the trailhead.")))
                } else if (post["answer"]?.toLowerCase() == "te anau") {
                    call.respond(FreeMarkerContent("5_city.ftl", mapOf("error" to "It's a pretty town, but too far away. Maybe he's someplace further northeast?")))
                } else {
                    call.respond(FreeMarkerContent("5_city.ftl", mapOf("error" to "I'm afraid that isn't it. If only we had a map!")))
                }
            }
        }

        route("/brown") {
            get {
                call.respond(FreeMarkerContent("6_zune.ftl", Zune1))
            }
            post {
                val post = call.receiveParameters()
                val index = post["index"]
                if (index == "1") {
                    call.respond(FreeMarkerContent("6_zune.ftl", Zune2))
                } else {
                    call.respondRedirect("/roadtrip", permanent = false)
                }
            }
        }

        route("/roadtrip") {
            get {
                val tracks = listOf("I Remember California", "California Love", "California Gurls", "Still In Hollywood")
                call.respond(FreeMarkerContent("7_playlist.ftl", mapOf("tracks" to tracks)))
            }
            post {
                val post = call.receiveParameters()
                val guess = post["tracks"]
                if (guess == "California Love") {
                    call.respondRedirect("/hacktheplanet", permanent = false)
                } else {
                    call.respondRedirect("/uhoh", permanent = false)
                }
            }
        }

        route("/uhoh") {
            get {
                call.respond(FreeMarkerContent("7a_doj.ftl", null))
            }
        }

        route("/hacktheplanet") {
            get {
                call.respond(FreeMarkerContent("8_convicted.ftl", null))
            }
            post {
                call.respondRedirect("/swordfish", permanent = false)
            }
        }

        route("/swordfish") {
            get {
                call.respond(FreeMarkerContent("9_hacking_cta.ftl", null))
            }
        }

        route("/lexencrypt") {
            get {
                call.respond(FreeMarkerContent("10_lexencrypt.ftl", null))
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

@Location("/type/{name}")
data class Type(val name: String) {
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
