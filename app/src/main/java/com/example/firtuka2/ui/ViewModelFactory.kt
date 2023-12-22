package com.example.firtuka2.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firtuka2.data.di.Injection
import com.example.firtuka2.data.repo.GlassesRepository
import com.example.firtuka2.ui.home.HomeViewModel

class ViewModelFactory(private val repository: GlassesRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context) : ViewModelFactory {
            if(INSTANCE == null){
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepo(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }


    }

}