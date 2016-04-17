package com.scheddy.data.realm.dao

import com.scheddy.data.realm.entity.CategoryEntity
import io.realm.Realm
import rx.Observable

class CategoryDao(val realm: Realm) {

  fun put(categoryEntity: CategoryEntity) {
    realm.executeTransactionAsync {
      realm.copyToRealm(categoryEntity)
    }
  }

  fun delete(categoryEntity: CategoryEntity) {
    realm.where(CategoryEntity::class.java)
        .equalTo("id", categoryEntity.id)
        .findFirstAsync()
        .asObservable<CategoryEntity>()
        .subscribe { result ->
          result.removeFromRealm()
        }
  }

  fun findAll(): Observable<List<CategoryEntity>> {
    return realm.where(CategoryEntity::class.java)
        .findAllAsync()
        .asObservable()
        .first()
        .map { it.toList() }
  }

}