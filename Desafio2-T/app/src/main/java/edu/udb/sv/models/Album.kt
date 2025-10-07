package edu.udb.sv.models

data class Album(
    val id: Int,
    val titulo: String,
    val artistaId: Int,
    val año: Int,
    val genero: String,
    val portada: String = "",
    val nombreArtista: String = ""
)