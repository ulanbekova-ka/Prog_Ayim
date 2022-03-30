package com.kay.prog.ayim.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.ayim.App
import com.kay.prog.ayim.R
import com.kay.prog.ayim.database.CharacterEntity
import com.kay.prog.ayim.extensions.toCharacterEntity
import com.kay.prog.ayim.ui.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

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
            getApplication<App>().rickAndMortyApi.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .map { response ->
                    val list = mutableListOf<CharacterEntity>()
                    response.results.forEach {
                        list.add(it.toCharacterEntity())
                    }
                    list.toList()
                }
                .map {
                    getApplication<App>().database.characterDao().insertList(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
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

}