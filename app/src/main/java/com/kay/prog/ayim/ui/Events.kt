package com.kay.prog.ayim.ui

import androidx.annotation.StringRes
import com.kay.prog.ayim.data.models.CharacterEntity

sealed class Event {
    class ShowToast(@StringRes val resId: Int): Event()
    object ShowLoading: Event()
    object StopLoading: Event()
    class FetchedCharacter(val character: CharacterEntity) : Event()
}