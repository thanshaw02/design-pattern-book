package com.example.backend.controllers

import com.example.backend.db.DesignPatternDao
import com.example.backend.models.DesignPattern
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DesignPatternController(
  private val designPatternDao: DesignPatternDao
) {
  suspend fun addPattern(designPattern: DesignPattern) {
    return designPatternDao.addPattern(designPattern)
  }

  suspend fun editPattern(id: String, designPattern: DesignPattern) {
    return designPatternDao.editPattern(id, designPattern)
  }

  suspend fun getAllPatterns(): MutableList<DesignPattern> {
    return designPatternDao.getAllPatterns()
  }

  suspend fun getPatternById(id: String): DesignPattern {
    return designPatternDao.getPatternById(id)
  }

  suspend fun removePattern(id: String) {
    return designPatternDao.removePattern(id)
  }
}
