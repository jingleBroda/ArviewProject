package com.example.arviewproject.app.di.module

import androidx.room.Room
import com.example.arviewproject.app.App
import com.example.arviewprojectdata.data.room.ArviewProjectDaoDB
import com.example.arviewprojectdata.data.room.ArviewProjectRoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun createDB(app: App): ArviewProjectRoomDB {
        return Room.databaseBuilder(app,
            ArviewProjectRoomDB::class.java,
            "TwitchTopGames")
            .build()
    }

    @Singleton
    @Provides
    fun getDAO(db: ArviewProjectRoomDB): ArviewProjectDaoDB {
        return db.databaseDao()
    }
}