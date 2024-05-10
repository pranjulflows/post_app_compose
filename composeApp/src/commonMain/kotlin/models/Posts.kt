package models

import kotlinx.serialization.Serializable


@Serializable
data class Posts(
    val posts: List<Post> = emptyList(),
    val total: Int?=null,
    val skip: Int?=null,
    val limit: Int?=null,

    )

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int,
    val tags: List<String>,
    val reactions: Int,
)
