package id.isato.emina.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import id.isato.emina.data.source.local.room.AnimeDao
import id.isato.emina.data.source.local.room.AnimeDatabase
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun providesTestDatabase(
        @ApplicationContext context: Context
    ): AnimeDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AnimeDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun providesTestDao(db: AnimeDatabase): AnimeDao = db.animeDao()

}