package com.gothouses.components

import com.gothouses.presenters.HousesActivityPresenter
import com.gothouses.scopes.HousesActivityScope
import com.gothouses.ui.HousesActivity
import dagger.Component

@HousesActivityScope
@Component(dependencies = [GotApplicationComponent::class])
interface HousesActivityComponent {

    fun injectHousesActivity(housesActivity: HousesActivity)
    fun injectHousesActivityPresenter(housesActivityPresenter: HousesActivityPresenter)
}