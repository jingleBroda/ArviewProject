package com.example.arviewprojectdata.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.arviewprojectdata.data.entity.TopTwitchGameEntity

@Database(entities = [TopTwitchGameEntity::class], version = 1, exportSchema = false)
abstract class ArviewProjectRoomDB: RoomDatabase() {
    abstract fun databaseDao(): ArviewProjectDaoDB
}