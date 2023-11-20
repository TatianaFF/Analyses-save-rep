package com.example.myapp.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.myapp.AppDatabase
import com.example.myapp.repository.AnalysisRepositoryImpl
import com.example.myapp.screens.fragments.list_analyses.ListAnalysesFragment
import com.example.myapp.screens.fragments.list_analyses.ListAnalysesViewModel
//import com.example.myapp.screens.fragments.list_analyses.VMFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.getDao()

//    //Provide Factory
//    @Provides
//    fun provideFactory(repositoryImpl: AnalysisRepositoryImpl, idOrgan: Long): VMFactory {
//        return VMFactory(repositoryImpl, idOrgan)
//    }
//
//    //Provide actual viewmodel
//    @Provides
//    fun provideViewModel(vmFactory: VMFactory): ListAnalysesViewModel {
//        return ViewModelProvider(ListAnalysesFragment())[ListAnalysesViewModel::class.java]
//    }
}