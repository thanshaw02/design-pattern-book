package com.example.backend.providers

import com.example.backend.controllers.DesignPatternController
import com.example.backend.db.DesignPatternDao
import com.example.backend.models.DesignPattern
import java.util.*

// in memory design pattern cache instance
val inMemoryDesignPatterns: MutableList<DesignPattern> = mutableListOf(
  DesignPattern(
    id = UUID.randomUUID().toString(),
    name = "",
    forces = "",
    resolution = "",
    codeExamples = "",
    precedingPattern = "",
    followingPattern = ""
  ),
)

// dao instance
val designPatternDao = DesignPatternDao(inMemoryDesignPatterns)

// controller instance
val designPatternController = DesignPatternController(designPatternDao)
