package com.kay.prog.ayim.data.models

fun Item.toCharacterEntity(): CharacterEntity {

    return CharacterEntity(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        gender = this.gender,
        origin = this.origin.name,
        location = this.location.name,
        image = this.image
    )
}