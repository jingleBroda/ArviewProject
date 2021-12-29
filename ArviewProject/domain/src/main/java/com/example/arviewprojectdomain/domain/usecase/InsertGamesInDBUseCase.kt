package com.example.arviewprojectdomain.domain.usecase

import com.example.arviewprojectdomain.domain.model.ModelRetrofit
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch
import com.example.arviewprojectdomain.domain.repository.Repository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class InsertGamesInDBUseCase(private val repository: Repository) {
    fun doIt(item:DataTwitch): Completable {
        return repository.insertGamesInDB(item)
    }
}