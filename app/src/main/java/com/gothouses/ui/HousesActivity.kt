package com.gothouses.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gothouses.GotApplication
import com.gothouses.adapters.HousesAdapter
import com.gothouses.components.DaggerHousesActivityComponent
import com.gothouses.contracts.HousesActivityContract
import com.gothouses.models.House
import com.gothouses.presenters.HousesActivityPresenter
import com.gothouses.utils.Keys
import com.gothouses.utils.Utils
import kotlinx.android.synthetic.main.activity_houses.*
import javax.inject.Inject


class HousesActivity : AppCompatActivity(), HousesActivityContract.View {

    private lateinit var presenter: HousesActivityPresenter
    private lateinit var adapter: HousesAdapter

    private lateinit var housesData: MutableList<House>

    @Inject
    lateinit var gson: Gson

    /**
     * To detect end of list for pagination. (TBR by jetpack/Paging)
     */
    private lateinit var onScrollListener: RecyclerView.OnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.gothouses.R.layout.activity_houses)

        val component = DaggerHousesActivityComponent.builder()
            .gotApplicationComponent((application as GotApplication).gotApplicationComponent)
            .build()

        component.injectHousesActivity(this)

        var page: Int
        // recover data
        if (savedInstanceState != null) {
            housesData =
                gson.fromJson(
                    savedInstanceState.getString(Keys.HOUSES_DATA),
                    object : TypeToken<MutableList<House>>() {}.type
                )

            page = savedInstanceState.getInt(Keys.HOUSES_DATA_PAGE)
        } else {
            housesData = mutableListOf()
            page = 1
        }


        presenter = HousesActivityPresenter(this, component, page)
        presenter.start()

        presenter.loadHouses()
    }

    override fun init() {
        // setup layout manager for recyclerview
        val linearLayoutManager = LinearLayoutManager(this)
        housesList.layoutManager = linearLayoutManager

        // setup adapter
        val onItemClick = object : HousesAdapter.OnItemClickListener {
            override fun onItemClick(item: House) {
                // open house detail

                Utils.startActivity(
                    this@HousesActivity,
                    Intent(this@HousesActivity, HouseDetailActivity::class.java).apply {
                        putExtra(Keys.HOUSE_DATA, item)
                    })
            }
        }
        adapter = HousesAdapter(housesData, onItemClick)

        // set adapter
        housesList.adapter = adapter

        // add scroll to bottom listener
        onScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    // load next page
                    val apiCalled = presenter.loadHouses()

                    // remove listener
                    if (!apiCalled) housesList.removeOnScrollListener(onScrollListener)
                }
            }
        }

        hideLoading()
    }

    override fun displayHouses(houses: List<House>) {
        housesData.addAll(houses)
        adapter.notifyDataSetChanged()
    }

    override fun displayTimeoutError() {
        Toast.makeText(this, com.gothouses.R.string.timeout_error, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
        housesList.removeOnScrollListener(onScrollListener)
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
        housesList.addOnScrollListener(onScrollListener)
    }

    override fun onStop() {
        super.onStop()
        presenter.destroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putString(Keys.HOUSES_DATA, gson.toJson(housesData))
        outState?.putInt(Keys.HOUSES_DATA_PAGE, presenter.page)
    }
}
