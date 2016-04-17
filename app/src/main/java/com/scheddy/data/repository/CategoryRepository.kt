package com.scheddy.data.repository

import com.scheddy.data.realm.dao.CategoryDao
import com.scheddy.data.realm.entity.CategoryEntity
import rx.Observable

class CategoryRepository(val categoryDao: CategoryDao) {

  fun saveCategory(category: CategoryEntity) {
    categoryDao.put(category)
  }

  fun getCategories(): Observable<CategoryEntity> {
    return categoryDao.findAll()
        .flatMap { result ->
          Observable.from(result)
        }
  }
}