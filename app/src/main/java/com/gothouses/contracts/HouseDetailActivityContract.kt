package com.gothouses.contracts

import com.gothouses.models.House

interface HouseDetailActivityContract {

    interface View {
        fun init()

        fun displayHouse(house: House)

        fun displayError()
    }

    interface Presenter {
        fun start()

        fun loadHouse(house: House?)
    }


}