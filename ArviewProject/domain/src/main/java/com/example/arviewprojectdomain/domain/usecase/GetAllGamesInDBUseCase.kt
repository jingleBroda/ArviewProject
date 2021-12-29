package com.example.arviewprojectdomain.domain.usecase

import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch
import com.example.arviewprojectdomain.domain.repository.Repository
import io.reactivex.rxjava3.core.Single

class GetAllGamesInDBUseCase(private val repository: Repository) {
    fun doIt(): Single<List<DataTwitch>> {
        return repository.getAllGamesInDB()
    }
}