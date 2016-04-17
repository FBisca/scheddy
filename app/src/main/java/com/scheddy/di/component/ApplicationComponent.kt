package com.scheddy.di.component

import com.scheddy.di.module.AndroidModule
import com.scheddy.di.module.DataModule
import com.scheddy.di.module.RealmModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AndroidModule::class,
        RealmModule::class,
        DataModule::class
    )
)
interface ApplicationComponent {
}