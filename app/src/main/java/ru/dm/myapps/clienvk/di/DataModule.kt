package ru.dm.myapps.clienvk.di

import android.app.Application
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.dm.myapps.clienvk.data.network.ApiFactory
import ru.dm.myapps.clienvk.data.network.ApiService
import ru.dm.myapps.clienvk.data.repository.NewsFeedRepositoryImpl
import ru.dm.myapps.clienvk.domain.NewsFeedRepository

@Module
interface DataModule {
    @Binds
    @AppScope
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {
        @Provides
        @AppScope
        fun provideApi(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        @AppScope
        fun provideVkStorage(application: Application): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(application)
        }
    }
}