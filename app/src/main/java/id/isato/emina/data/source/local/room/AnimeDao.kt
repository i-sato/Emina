package id.isato.emina.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.isato.emina.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
abstract class AnimeDao {

    @Query("SELECT * FROM anime")
    protected abstract fun getTopAnime(): Flow<List<AnimeEntity>>

    fun getDistinctTopAnime(): Flow<List<AnimeEntity>> =
        getTopAnime().distinctUntilChanged()

    @Query("SELECT * FROM anime WHERE favorite = '1'")
    protected abstract fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    fun getDistinctFavoriteAnime(): Flow<List<AnimeEntity>> = getFavoriteAnime().distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAnime(anime: List<AnimeEntity>)

    @Query("SELECT * FROM anime WHERE mal_id = :malId")
    abstract suspend fun getAnimeById(malId: Int): AnimeEntity

    @Query("UPDATE anime SET favorite = :isFavorite WHERE mal_id = :malId")
    abstract suspend fun updateFavorite(malId: Int, isFavorite: Boolean)

}