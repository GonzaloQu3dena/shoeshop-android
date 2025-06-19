package com.qu3dena.shoeshop.android.core.di

import android.content.Context
import androidx.room.Room
import com.qu3dena.shoeshop.android.catalog.data.source.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "shoe_shop_db"
        )
            // Allowing destructive migrations for simplicity, but in production, you should handle migrations properly.
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSneakerDao(
        database: AppDataBase
    ) = database.sneakerDao()
}