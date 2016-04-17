package com.scheddy

import android.app.Application
import com.scheddy.di.component.ApplicationComponent
import com.scheddy.di.component.DaggerApplicationComponent
import com.scheddy.di.module.AndroidModule

class App : Application() {

  val applicationComponent: ApplicationComponent by lazy {
    initInjection()
  }

  private fun initInjection() : ApplicationComponent {
    return DaggerApplicationComponent.builder()
        .androidModule(AndroidModule(this))
        .build()
  }
}