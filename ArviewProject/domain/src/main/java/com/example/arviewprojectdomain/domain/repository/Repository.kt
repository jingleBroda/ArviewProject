package com.example.arviewprojectdomain.domain.repository

import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import io.reactivex.rxjava3.core.Single

abstract class Repository {

    abstract fun makeApi(): Single<ModelRetrofit>

}