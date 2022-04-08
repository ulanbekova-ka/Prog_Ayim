package com.kay.prog.ayim.data.models

data class ResponseDto(
    val results: MutableList<Item>
)

data class Item(
    val id: Long,
    var name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String?
)

data class Location(
    val name: String,
    val url: String?
)

data class Origin(
    val name: String,
    val url: String?
)