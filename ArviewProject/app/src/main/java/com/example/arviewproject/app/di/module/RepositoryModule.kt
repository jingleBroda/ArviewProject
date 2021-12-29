package com.example.arviewproject.app.di.module

import com.example.arviewprojectdata.data.repositoryImpl.RepositoryImplement
import com.example.arviewprojectdata.data.retrofit.RetrofitService
import com.example.arviewprojectdomain.domain.repository.Repository
import com.example.arviewprojectdomain.domain.usecase.MakeApiUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun makeRepository(
        retroService: RetrofitService
    ):Repository{
        return RepositoryImplement(retroService)
    }

    @Provides
    fun makeApiUseCase(
        repository:Repository
    ):MakeApiUseCase{
        return MakeApiUseCase(repository)
    }

}