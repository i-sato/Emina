@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused")

package id.isato.emina.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.isato.emina.data.source.local.LocalDataSource
import id.isato.emina.data.source.local.LocalDataSourceImpl
import id.isato.emina.data.source.remote.RemoteDataSource
import id.isato.emina.data.source.remote.RemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindLocalDataSource(local: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remote: RemoteDataSourceImpl): RemoteDataSource

}