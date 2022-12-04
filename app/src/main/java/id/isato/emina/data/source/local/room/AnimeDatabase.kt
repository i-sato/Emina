package id.isato.emina.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.isato.emina.data.source.local.entity.AnimeEntity

@Database(
    entities = [
        AnimeEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AnimeDatabase: RoomDatabase() {

    abstract fun animeDao(): AnimeDao

}