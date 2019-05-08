package com.gothouses.api

import com.gothouses.models.House
import com.gothouses.utils.Keys
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GotService {

    @GET(Keys.API_HOUSES)
    fun getHouses(@Query("page") page: Int): Observable<List<House>>

//    @GET(Keys.API_CHARACTER)
//    fun get(@Path("{id}") id: Int): Observable<>

}
