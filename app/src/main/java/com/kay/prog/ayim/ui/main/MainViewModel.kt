package com.kay.prog.ayim.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kay.prog.ayim.App
import com.kay.prog.ayim.api.Response
import com.kay.prog.ayim.database.CharacterEntity
import com.kay.prog.ayim.extensions.toCharacterEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val charactersLiveData: LiveData<List<CharacterEntity>> =
        getApplication<App>().database.characterDao().getAll()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        compositeDisposable.add(
            getApplication<App>().rickAndMortyApi.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { response ->
                    val list = mutableListOf<CharacterEntity>()
                    response.results.forEach {
                        list.add(it.toCharacterEntity())
                    }
                    getApplication<App>().database.characterDao().insertList(list)
                }
                .onErrorReturnItem(
                    Response(mutableListOf())
                )
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}