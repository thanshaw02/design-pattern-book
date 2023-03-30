package com.example.backend.errors

class DesignPatternIOError(
  message: String? = null,
  cause: Throwable? = null
) : Exception(message, cause)
