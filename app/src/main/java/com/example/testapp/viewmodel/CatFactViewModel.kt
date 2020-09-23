package com.example.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.model.data.CatFact
import com.example.testapp.model.service.ServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CatFactViewModel(private val serviceBuilder: ServiceBuilder): ViewModel() {

    private lateinit var subscription: Disposable
    val catFacts = MutableLiveData<MutableList<CatFact>>()

    fun refresh() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
        fetchFact()
    }

    fun fetchFact() {
        subscription = ServiceBuilder.buildCatFactService().getCatFact()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {response ->
                    val tempList = catFacts.value ?: mutableListOf()
                    tempList.add(response)
                    catFacts.postValue(tempList)
                },
                {t ->  }
            )
    }

}
