package com.gothouses.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gothouses.R
import com.gothouses.contracts.HouseDetailActivityContract
import com.gothouses.models.House
import com.gothouses.presenters.HouseDetailActivityPresenter
import com.gothouses.utils.Keys
import com.gothouses.utils.valueOrDash
import kotlinx.android.synthetic.main.activity_house_detail.*

class HouseDetailActivity : AppCompatActivity(), HouseDetailActivityContract.View {

    private lateinit var presenter: HouseDetailActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_detail)

        presenter = HouseDetailActivityPresenter(this)
        presenter.start()

    }

    override fun init() {
        val house =
            if (intent.extras.containsKey(Keys.HOUSE_DATA)) intent.extras.getParcelable<House>(Keys.HOUSE_DATA) else null

        presenter.loadHouse(house)
    }

    override fun displayHouse(house: House) {
        houseName.text = house.name.valueOrDash()
        houseRegion.text = house.region.valueOrDash()
        houseWords.text = house.words.valueOrDash()
    }

    override fun displayError() {
        Toast.makeText(this, com.gothouses.R.string.house_detail_error, Toast.LENGTH_SHORT).show()
        finish()
    }
}
