package com.szn.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in Application
    @Provides
    fun provideDatabase(@ApplicationContext app: Context) = AppDatabase.getDatabase(app)

    @Singleton
    @Provides
    fun provideQuoteDao(db: AppDatabase) = db.movieDao()

}