package id.isato.emina.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "anime", indices = [Index(value = ["mal_id"], unique = true)])
data class AnimeEntity(

    @PrimaryKey
    @ColumnInfo(name = "mal_id")
    val malId: Int,

    val title: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    val synopsis: String?,

    val favorite: Boolean = false

)
