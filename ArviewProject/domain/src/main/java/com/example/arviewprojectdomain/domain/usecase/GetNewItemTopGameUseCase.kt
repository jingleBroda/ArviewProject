package com.example.arviewprojectdomain.domain.usecase

import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.repository.Repository
import io.reactivex.rxjava3.core.Single

class GetNewItemTopGameUseCase(private val repository: Repository) {
    fun doIt(pagination:String, first:Int): Single<ModelRetrofit> {
        return repository.getNewItemTopGame(pagination, first)
    }
}