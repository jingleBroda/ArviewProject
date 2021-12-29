package com.example.arviewproject.app.di.module

import com.example.arviewprojectdata.data.repositoryImpl.RepositoryImplement
import com.example.arviewprojectdata.data.retrofit.RetrofitService
import com.example.arviewprojectdata.data.room.ArviewProjectDaoDB
import com.example.arviewprojectdomain.domain.repository.Repository
import com.example.arviewprojectdomain.domain.usecase.GetAllGamesInDBUseCase
import com.example.arviewprojectdomain.domain.usecase.GetNewItemTopGameUseCase
import com.example.arviewprojectdomain.domain.usecase.InsertGamesInDBUseCase
import com.example.arviewprojectdomain.domain.usecase.MakeApiUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun makeRepository(
        retroService: RetrofitService,
        dbDao:ArviewProjectDaoDB
    ):Repository{
        return RepositoryImplement(retroService,dbDao)
    }

    @Provides
    fun makeApiUseCase(
        repository:Repository
    ):MakeApiUseCase{
        return MakeApiUseCase(repository)
    }

    @Provides
    fun getNewItemTopGameUseCase(
        repository:Repository
    ):GetNewItemTopGameUseCase{
        return GetNewItemTopGameUseCase(repository)
    }

    @Provides
    fun getAllGamesInDB(
        repository:Repository
    ):GetAllGamesInDBUseCase{
        return GetAllGamesInDBUseCase(repository)
    }

    @Provides
    fun getInsertGamesInDbUseCase(
        repository:Repository
    ): InsertGamesInDBUseCase {
        return InsertGamesInDBUseCase(repository)
    }

}