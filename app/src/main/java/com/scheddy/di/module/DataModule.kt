package com.scheddy.di.module

import com.scheddy.data.realm.dao.CategoryDao
import com.scheddy.data.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Singleton
@Module
class DataModule {

  @Provides
  fun providesCategoryDao(realm: Realm) : CategoryDao {
    return CategoryDao(realm)
  }

  @Provides
  fun providesCategoryRepository(categoryDao: CategoryDao) : CategoryRepository {
    return CategoryRepository(categoryDao)
  }
}