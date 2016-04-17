package com.scheddy.data.realm

import io.realm.DynamicRealm
import io.realm.RealmMigration

class MigrationHandler : RealmMigration {

  override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
    // Not yet needed
  }
}