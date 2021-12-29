package com.example.arviewprojectdomain.domain.repository

import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

abstract class Repository {

    abstract fun makeApi(): Single<ModelRetrofit>
    abstract fun getNewItemTopGame(pagination:String, first:Int):Single<ModelRetrofit>

    abstract fun getAllGamesInDB(): Single<List<DataTwitch>>
    abstract fun insertGamesInDB(item:DataTwitch): Completable



}