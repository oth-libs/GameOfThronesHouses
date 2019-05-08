package com.gothouses.contracts

import com.gothouses.models.House

interface HousesActivityContract {

    interface View {
        fun init()

        fun displayHouses(houses: List<House>)

        fun displayTimeoutError()

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter {
        fun start()

        fun loadHouses(): Boolean

        fun destroy()
    }


}