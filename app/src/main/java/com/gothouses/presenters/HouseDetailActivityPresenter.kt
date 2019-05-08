package com.gothouses.presenters

import com.gothouses.contracts.HouseDetailActivityContract
import com.gothouses.models.House


class HouseDetailActivityPresenter(
    private val houseDetailActivityView: HouseDetailActivityContract.View
) : HouseDetailActivityContract.Presenter {

    override fun start() {

        // init activity views
        houseDetailActivityView.init()
    }

    override fun loadHouse(house: House?) {
        if (house == null)
            houseDetailActivityView.displayError()
        else
            houseDetailActivityView.displayHouse(house)
    }

}