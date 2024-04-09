package com.evarela.ucalifes02.model

data class PostDataClass(
    val id: Int,
    val title: String,
    val postImageId: Int,
    val likeCount: Int,
    val date: String,
    val description: String
)
