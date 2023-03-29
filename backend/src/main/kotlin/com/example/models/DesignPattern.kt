package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class DesignPattern(
  val id: String,
  val name: String,
  val forces: String,
  val resolution: String,
  val codeExample: String,
  val precedingPattern: String,
  val followingPattern: String
)

// storing the design patterns in memory until everything is working
// then will switch to PSQL
val inMemoryDesignPatterns = mutableListOf<DesignPattern>(
  DesignPattern(
    id = "some_id",
    name = "Some pattern",
    forces = "Some forces",
    resolution = "Some resolution",
    codeExample = "Some code example",
    precedingPattern = "Some preceding pattern",
    followingPattern = "Some following pattern"
  )
)
