package com.example.arviewprojectdata.data.repositoryImpl

import com.example.arviewprojectdata.data.retrofit.RetrofitService
import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.repository.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImplement @Inject constructor(val retrofitServise:RetrofitService): Repository() {

    override fun makeApi(): Single<ModelRetrofit> {
       return retrofitServise.getTopGameStartAPI(5)
    }
}