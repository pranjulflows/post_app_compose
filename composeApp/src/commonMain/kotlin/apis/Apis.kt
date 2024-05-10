package apis

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

import io.ktor.serialization.kotlinx.json.*

import models.Posts

class Apis {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getPosts(): Posts {
//        try {
            val result = httpClient.get("https://dummyjson.com/posts").body<Posts>()
            return result
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return Json.decodeFromString<Posts>(result.bodyAsText())
    }
}