package com.kay.prog.ayim.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.ayim.App
import com.kay.prog.ayim.ui.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var id: Long = -1
    fun setId(id: Long?) {
        this.id = id ?: -1
    }

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?>
        get() = _event

    fun fetchCharacter() {
        compositeDisposable.add(
            getApplication<App>()
                .database.characterDao().getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    _event.value = Event.FetchedCharacter(it)
                }
                .subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}