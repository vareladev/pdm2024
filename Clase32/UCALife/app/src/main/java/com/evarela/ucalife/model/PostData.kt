package com.evarela.ucalife.model

data class PostDataModel(
    val id: Int,
    val title: String,
    val image: Int,
    val likeCount: Int = 0,
    val date: String,
    val description: String
)
