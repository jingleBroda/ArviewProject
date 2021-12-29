package com.example.arviewprojectdata.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.arviewprojectdata.data.entity.TopTwitchGameEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ArviewProjectDaoDB {
    @Query("SELECT * FROM TopTwitchGame")
    fun getAllTopGames(): Single<List<TopTwitchGameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(electCurrency: TopTwitchGameEntity) : Completable

    @Query("DELETE FROM TopTwitchGame")
    fun deleteAllData(): Completable

    @Query("DELETE FROM TopTwitchGame WHERE id = :id")
    fun deleteSingleData(id:Int): Completable
}