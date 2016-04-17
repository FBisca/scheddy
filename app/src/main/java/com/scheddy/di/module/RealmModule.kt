package com.scheddy.di.module

import android.content.Context
import com.scheddy.data.realm.MigrationHandler
import com.scheddy.data.realm.RealmConfig
import com.scheddy.data.realm.dao.CategoryDao
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration
import javax.inject.Singleton

@Singleton
@Module
class RealmModule {

  @Provides
  fun providesRealmConfiguration(context: Context, migration: RealmMigration): RealmConfiguration {
    return RealmConfiguration.Builder(context)
        .name(RealmConfig.NAME)
        .schemaVersion(RealmConfig.VERSIONS.last())
        .migration(migration)
        .build()
  }

  @Provides
  fun providesRealmMigration(): RealmMigration {
    return MigrationHandler()
  }

  @Provides
  fun providesRealm(configuration: RealmConfiguration) : Realm {
    return Realm.getInstance(configuration)
  }

}