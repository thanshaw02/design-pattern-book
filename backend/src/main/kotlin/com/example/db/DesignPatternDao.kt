package com.example.db

import com.example.models.DesignPattern
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.Statement

class DesignPatternDao(private val connection: Connection) {
  companion object {
    private const val CREATE_TABLE_DESIGN_PATTERNS =
      "CREATE TABLE design_patterns " +
              "(id TEXT NOT NULL PRIMARY KEY, " +
              "name TEXT NOT NULL, " +
              "forces TEXT NOT NULL, " +
              "resolution  TEXT NOT NULL, " +
              "code_example TEXT NOT NULL, " +
              "preceding_pattern TEXT NOT NULL, " +
              "following_pattern TEXT NOT NULL);"
    private const val SELECT_PATTERN_BY_ID = "SELECT * FROM design_patterns WHERE id = ?"
    private const val SELECT_ALL_PATTERNS = "SELECT * FROM design_patterns"
    private const val INSERT_PATTERN =
      "INSERT INTO design_patterns " +
            "(id, name, forces, resolution, code_example, preceding_pattern, following_pattern) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?)"
    private const val UPDATE_PATTERN =
      "UPDATE design_patterns SET " +
              "name = ?, forces = ?, resolution = ?, code_example = ?, preceding_pattern = ?, following_pattern = ? " +
              "WHERE id = ?"
    private const val DELETE_PATTERN = "DELETE FROM design_patterns WHERE id = ?"

  }

  init {
    connection.createStatement().executeUpdate(CREATE_TABLE_DESIGN_PATTERNS)
  }

  // Create new design pattern
  suspend fun create(designPattern: DesignPattern): Int = withContext(Dispatchers.IO) {
    val statement = connection.prepareStatement(INSERT_PATTERN, Statement.RETURN_GENERATED_KEYS)
    statement.setString(1, designPattern.id)
    statement.setString(2, designPattern.name)
    statement.setString(3, designPattern.forces)
    statement.setString(4, designPattern.resolution)
    statement.setString(5, designPattern.codeExample)
    statement.setString(6, designPattern.precedingPattern)
    statement.setString(7, designPattern.followingPattern)
    statement.executeUpdate()

    val generatedKeys = statement.generatedKeys
    if (generatedKeys.next()) {
      return@withContext generatedKeys.getInt(1)
    } else {
      throw Exception("Unable to retrieve the id of the newly inserted design pattern")
    }
  }

  suspend fun readAll(): List<DesignPattern> = withContext(Dispatchers.IO) {
    val statement = connection.prepareStatement(SELECT_ALL_PATTERNS)
  }

  // Read a design pattern
  suspend fun read(id: String): DesignPattern = withContext(Dispatchers.IO) {
    val statement = connection.prepareStatement(SELECT_PATTERN_BY_ID)
    statement.setString(1, id)
    val resultSet = statement.executeQuery()

    if (resultSet.next()) {
      return@withContext DesignPattern(
        id = id,
        name = resultSet.getString("name"),
        forces = resultSet.getString("forces"),
        resolution = resultSet.getString("resolution"),
        codeExample = resultSet.getString("code_example"),
        precedingPattern = resultSet.getString("preceding_pattern"),
        followingPattern = resultSet.getString("following_pattern")
      )
    } else {
      throw Exception("Record not found")
    }
  }

  // Update a design pattern
  suspend fun update(id: String, designPattern: DesignPattern) = withContext(Dispatchers.IO) {
    val statement = connection.prepareStatement(UPDATE_PATTERN)
    statement.setString(1, designPattern.id)
    statement.setString(2, designPattern.name)
    statement.setString(3, designPattern.forces)
    statement.setString(4, designPattern.resolution)
    statement.setString(5, designPattern.codeExample)
    statement.setString(6, designPattern.precedingPattern)
    statement.setString(7, designPattern.followingPattern)
    statement.executeUpdate()
  }

  // Delete a design pattern
  suspend fun delete(id: String) = withContext(Dispatchers.IO) {
    val statement = connection.prepareStatement(DELETE_PATTERN)
    statement.setString(1, id)
    statement.executeUpdate()
  }
}