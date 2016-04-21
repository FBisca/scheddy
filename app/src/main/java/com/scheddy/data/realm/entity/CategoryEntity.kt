package com.scheddy.data.realm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class CategoryEntity() : RealmObject() {

  @PrimaryKey
  var id: Long? = null
  @Required
  var name: String? = null
  var color: Int? = null

  constructor(id: Long?, name: String?, color: Int?) : this() {
    this.id = id
    this.name = name
    this.color = color
  }
}
