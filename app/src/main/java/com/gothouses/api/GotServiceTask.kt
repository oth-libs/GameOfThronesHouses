package com.gothouses.api

import com.gothouses.callbacks.Callback
import com.gothouses.models.House
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GotServiceTask @Inject constructor(var gotService: GotService) {

    fun getHouses(callback: Callback<List<House>>, page: Int): Disposable {

        return gotService.getHouses(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({

                callback.returnResult(it)
            }, {

                callback.returnError()
            })
    }
}
