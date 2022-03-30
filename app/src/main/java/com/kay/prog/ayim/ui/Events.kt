package com.kay.prog.ayim.ui

import com.kay.prog.ayim.database.CharacterEntity

sealed class Event {
    class FetchedCharacter(val character: CharacterEntity) : Event()
}