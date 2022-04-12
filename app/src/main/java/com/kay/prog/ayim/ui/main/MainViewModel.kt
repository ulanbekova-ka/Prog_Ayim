package com.kay.prog.ayim.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.ayim.R
import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.domain.GetCharacterAsLiveDataUseCase
import com.kay.prog.ayim.domain.GetCharactersUseCase
import com.kay.prog.ayim.ui.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val getCharacterAsLiveDataUseCase: GetCharacterAsLiveDataUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
    ): AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val charactersLiveData: LiveData<List<CharacterEntity>> = getCharacterAsLiveDataUseCase()

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?>
        get() = _event

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        _event.value = Event.ShowLoading

        compositeDisposable.add(
           getCharactersUseCase()
                .doOnTerminate { _event.value = Event.StopLoading }
                .subscribe({}, {
                    handleError(it)
                })
        )
    }

    private fun handleError(it: Throwable) {
        _event.value = when (it) {
            is UnknownHostException -> Event.ShowToast(R.string.no_internet)
            else -> Event.ShowToast(R.string.unknown_error)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        clearEvents()
    }

    fun clearEvents() {
        _event.value = null
    }
}