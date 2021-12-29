package com.example.arviewprojectdata.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.arviewprojectdomain.domain.model.componentModelRetrofit.DataTwitch


@Entity(tableName = "TopTwitchGame")
data class TopTwitchGameEntity(
    @PrimaryKey
    val id:Int,
    val name:String,
    val box_art_url:String
){
    fun toDataTwith() = DataTwitch(
        id,
        name,
        box_art_url
    )

    companion object {
        fun toTopTwitchGameEntity(data: DataTwitch) =
            data.run {
                TopTwitchGameEntity(
                    id,
                    name,
                    box_art_url
                )
            }
        fun toDataTwitchlList(entitiesList: List<TopTwitchGameEntity>) =
            entitiesList.map(TopTwitchGameEntity::toDataTwith)

    }
}