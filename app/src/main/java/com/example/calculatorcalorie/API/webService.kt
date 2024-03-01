package com.example.calculatorcalorie.API

import retrofit2.http.GET
import io.reactivex.rxjava3.core.Observable

interface webService {
    @GET("calorie.txt?token=GHSAT0AAAAAAB4MBMIVUZ5OXHXRSGMBU6M2Y43RUMQ")
    fun getPosts(): Observable<List<calorie>>
}
