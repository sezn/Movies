package com.szn.core.datastore

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext app: Context): DataStoreManager = DataStoreManager(app)
}