package com.evarela.ucalifes01.model

data class PostDataModel(
    val id: Int,
    val title: String,
    val photo_id: Int,
    val likeCout: Int,
    val date: String,
    val description: String
)
