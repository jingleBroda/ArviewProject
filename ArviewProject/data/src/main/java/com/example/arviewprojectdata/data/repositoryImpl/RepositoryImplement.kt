package com.example.arviewprojectdata.data.repositoryImpl

import com.example.arviewprojectdata.data.entity.TopTwitchGameEntity
import com.example.arviewprojectdata.data.retrofit.RetrofitService
import com.example.arviewprojectdata.data.room.ArviewProjectDaoDB
import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch
import com.example.arviewprojectdomain.domain.repository.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImplement @Inject constructor(val retrofitServise:RetrofitService, val dbDao:ArviewProjectDaoDB): Repository() {

    override fun makeApi(): Single<ModelRetrofit> {
       return retrofitServise.getTopGameStartAPI(5)
    }

    override fun getNewItemTopGame(pagination:String, first:Int): Single<ModelRetrofit> {
        return retrofitServise.getNewListTopGame(pagination, first)
    }

    override fun getAllGamesInDB(): Single<List<DataTwitch>> {
        return dbDao.getAllTopGames().map(TopTwitchGameEntity::toDataTwitchlList)
    }

    override fun insertGamesInDB(item: DataTwitch): Completable {
        return dbDao.insertGame(TopTwitchGameEntity.toTopTwitchGameEntity(item))
    }
}