package com.gothouses.presenters

import com.gothouses.api.GotServiceTask
import com.gothouses.callbacks.Callback
import com.gothouses.components.HousesActivityComponent
import com.gothouses.contracts.HousesActivityContract
import com.gothouses.models.House
import com.gothouses.utils.Keys
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class HousesActivityPresenter(
    private val housesActivityView: HousesActivityContract.View,
    private val component: HousesActivityComponent,
    var page: Int
) : HousesActivityContract.Presenter {


    @Inject
    lateinit var gotServiceTask: GotServiceTask

    private var disposableGotService: Disposable? = null

    override fun start() {
        // add HousesActivityPresenter injection
        component.injectHousesActivityPresenter(this)

        // init activity views
        housesActivityView.init()
    }

    override fun loadHouses(): Boolean {
        // no more data
        if (page == -1) return false

        housesActivityView.showLoading()

        disposableGotService = gotServiceTask.getHouses(object : Callback<List<House>>() {
            override fun returnResult(houses: List<House>) {
                housesActivityView.hideLoading()

                housesActivityView.displayHouses(houses)

                if (houses.size == Keys.PAGE_SIZE) {
                    // prepare for next page
                    page++
                } else {
                    // end of data
                    page = -1
                }
            }

            override fun returnError() {
                housesActivityView.hideLoading()

                housesActivityView.displayTimeoutError()
            }
        }, page)

        return true
    }

    override fun destroy() {
        disposableGotService?.dispose()
    }


}