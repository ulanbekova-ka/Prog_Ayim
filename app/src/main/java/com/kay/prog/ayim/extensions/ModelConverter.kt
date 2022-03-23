package com.kay.prog.ayim.extensions

import com.kay.prog.ayim.api.Item
import com.kay.prog.ayim.database.CharacterEntity

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