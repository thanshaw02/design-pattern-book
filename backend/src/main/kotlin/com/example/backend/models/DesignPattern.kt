package com.example.backend.models

data class DesignPattern(
  var id: String,
  var name: String,
  var forces: String,
  var resolution: String,
  var codeExamples: String,
  var precedingPattern: String,
  var followingPattern: String
)
