package com.example.backend.db

import com.example.backend.models.DesignPattern
import kotlinx.coroutines.*

class DesignPatternDao {
  private val inMemoryDesignPatterns: MutableList<DesignPattern> = mutableListOf()

  suspend fun addPattern(designPattern: DesignPattern) = withContext(Dispatchers.IO) {
    inMemoryDesignPatterns.add(designPattern);
  }

  suspend fun editPattern(id: String, updatedPattern: DesignPattern) = withContext(Dispatchers.IO) {
    val patternToUpdate = inMemoryDesignPatterns.find { it.id == id }
    if (patternToUpdate == null) {
      
    }
  }

  suspend fun getAllPatterns(): MutableList<DesignPattern> = withContext(Dispatchers.IO) {

  }

  suspend fun getPatternById(id: String): DesignPattern = withContext(Dispatchers.IO) {

  }

  suspend fun removePattern(id: String) = withContext(Dispatchers.IO) {

  }
}
