package com.example.backend.models

import java.util.UUID

data class DesignPattern(
  val id: String = UUID.randomUUID().toString(),
  var name: String,
  var forces: String,
  var resolution: String,
  var codeExamples: String,
  var precedingPatter: String,
  var followingPattern: String
)
