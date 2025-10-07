package edu.udb.sv.models

data class Cancion(
    val id: Int,
    val titulo: String,
    val artistaId: Int,
    val albumId: Int,
    val duracion: String,
    val genero: String,
    val nombreArtista: String = ""
)