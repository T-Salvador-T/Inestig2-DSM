package edu.udb.sv.models


data class Artista(
    val id: Int,
    val nombre: String,
    val genero: String,
    val descripcion: String,
    val imagen: String = ""
)