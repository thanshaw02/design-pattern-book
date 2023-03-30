package com.example.backend.db

import com.example.backend.errors.DesignPatternIOError
import com.example.backend.models.DesignPattern
import kotlinx.coroutines.*

class DesignPatternDao(
  private val inMemoryDesignPatterns: MutableList<DesignPattern>
) {
  suspend fun addPattern(designPattern: DesignPattern): Unit = withContext(Dispatchers.IO) {
    inMemoryDesignPatterns.add(designPattern);
  }

  suspend fun editPattern(id: String, updatedPattern: DesignPattern): Unit = withContext(Dispatchers.IO) {
    val patternToUpdate = inMemoryDesignPatterns.find { it.id == id }
      ?: throw DesignPatternIOError("Design pattern to update was not found with id $id")

    val patternIndex = inMemoryDesignPatterns.indexOf(patternToUpdate)
    inMemoryDesignPatterns[patternIndex] = updatedPattern
  }

  suspend fun getAllPatterns(): MutableList<DesignPattern> = withContext(Dispatchers.IO) {
    return@withContext inMemoryDesignPatterns
  }

  suspend fun getPatternById(id: String): DesignPattern = withContext(Dispatchers.IO) {
    return@withContext inMemoryDesignPatterns.find { it.id == id }
      ?: throw DesignPatternIOError("Could not find design pattern with id $id")
  }

  suspend fun removePattern(id: String): Unit = withContext(Dispatchers.IO) {
    if (!(inMemoryDesignPatterns.removeIf { it.id == id })) {
      throw DesignPatternIOError("Design pattern to remove was not found with id $id")
    }
  }
}
