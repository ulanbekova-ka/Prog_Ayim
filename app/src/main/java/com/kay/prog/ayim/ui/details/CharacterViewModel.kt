package com.kay.prog.ayim.ui.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kay.prog.ayim.domain.GetCharacterByIdUseCase
import com.kay.prog.ayim.ui.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    application: Application,
    private val getCharacterByIdCase: GetCharacterByIdUseCase
): AndroidViewModel(application) {

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
            getCharacterByIdCase(id)
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