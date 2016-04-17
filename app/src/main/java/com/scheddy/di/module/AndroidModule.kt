package com.scheddy.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class AndroidModule(private val context: Context) {

  @Provides
  fun providesContext() : Context {
    return context
  }
}