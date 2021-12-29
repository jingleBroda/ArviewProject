package com.example.arviewprojectdomain.domain.usecase

import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.repository.Repository
import io.reactivex.rxjava3.core.Single

class MakeApiUseCase(private val repository: Repository) {
    fun doIt(): Single<ModelRetrofit> {
        return repository.makeApi()
    }
}