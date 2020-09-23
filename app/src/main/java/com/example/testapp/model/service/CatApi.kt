package com.example.testapp.model.service


import com.example.testapp.model.data.CatFact
import com.example.testapp.model.data.CatImage
import io.reactivex.Observable
import retrofit2.http.GET

const val catImageQuery = "/meow"
const val catFactQuery = "/facts/random"

interface CatImageApi {

    @GET(catImageQuery)
    fun getCatImage(): Observable<CatImage>

}

interface CatFactApi {
    @GET(catFactQuery)
    fun getCatFact(): Observable<CatFact>
}