package id.isato.emina.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.isato.emina.BuildConfig
import id.isato.emina.data.source.local.room.AnimeDao
import id.isato.emina.data.source.local.room.AnimeDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): AnimeDatabase {
        val dbBuilder = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime.db"
        ).also { builder ->
            when {
                BuildConfig.DEBUG -> builder.fallbackToDestructiveMigration()
                else -> {
                    val factory = SupportFactory(SQLiteDatabase.getBytes(BuildConfig.DB_KEY.toCharArray()))
                    builder.openHelperFactory(factory)
                }
            }
        }
        return dbBuilder.build()
    }

    @Provides
    @Singleton
    fun providesAnimeDao(db: AnimeDatabase): AnimeDao = db.animeDao()

}