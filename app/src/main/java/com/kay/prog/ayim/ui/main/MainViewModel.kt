package com.kay.prog.ayim.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.ayim.App
import com.kay.prog.ayim.R
import com.kay.prog.ayim.data.models.CharacterEntity
import com.kay.prog.ayim.data.repo.RickAndMortyRepo
import com.kay.prog.ayim.domain.GetCharactersUseCase
import com.kay.prog.ayim.ui.Event
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val repo = RickAndMortyRepo(
        getApplication<App>().rickAndMortyApi,
        getApplication<App>().database.characterDao()
    )

    private val getCharactersUseCase = GetCharactersUseCase(repo)

    val charactersLiveData: LiveData<List<CharacterEntity>> =
        getApplication<App>().database.characterDao().getAll()

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
    }

    fun clearEvents() {
        _event.value = null
    }
}