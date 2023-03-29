package com.example.controllers

import com.example.db.DesignPatternDao
import com.example.models.DesignPattern

class DesignPatternController(
  private val designPatternDao: DesignPatternDao
) {
  fun getAllDesignPatterns(): List<DesignPattern> {

  }

  fun getDesignPatternById(id: String): DesignPattern {

  }

  fun addDesignPattern(designPattern: DesignPattern) {

  }

  fun updateDesignPattern(id: String, designPattern: DesignPattern) {

  }

  fun deleteDesignPattern(id: String) {

  }
}