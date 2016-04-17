package com.scheddy.data.realm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

class CategoryEntity(
    @PrimaryKey val id: Long,
    @Required val name: String,
    val color: Int
) : RealmObject()