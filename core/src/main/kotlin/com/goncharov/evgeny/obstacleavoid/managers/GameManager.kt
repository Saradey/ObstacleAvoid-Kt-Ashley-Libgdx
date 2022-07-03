package com.goncharov.evgeny.obstacleavoid.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.goncharov.evgeny.obstacleavoid.consts.LIVES_START
import com.goncharov.evgeny.obstacleavoid.models.DifficultyLevel

//object GameManager {
//
//    private var highScore = 0
//    private var difficultyLevel = DifficultyLevel.MEDIUM
//    var score = 0
//    var lives = LIVES_START
//
//    private val prefs: Preferences by lazy {
//        Gdx.app.getPreferences(PREFS_NAME)
//    }
//
//    fun getHighScore(): Int {
//        highScore = prefs.getInteger(HIGH_SCORE_KEY, 0)
//        return highScore
//    }
//
//    fun getDifficultyLevel(): DifficultyLevel {
//        difficultyLevel = DifficultyLevel.valueOf(
//            prefs.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name)
//        )
//        return difficultyLevel
//    }
//
//    fun updateHighScore() {
//        if (score < getHighScore()) {
//            return
//        }
//        highScore = score
//        prefs.putInteger(HIGH_SCORE_KEY, highScore)
//        prefs.flush()
//    }
//
//    fun reset() {
//        lives = LIVES_START
//        score = 0
//    }
//
//    fun updateScore(amount: Int) {
//        score += amount
//    }
//
//    fun decrementLives() {
//        lives--
//    }
//
//    fun isGameOver(): Boolean = lives <= 0
//
//    fun updateDifficulty(newDifficultyLevel: DifficultyLevel) {
//        if (newDifficultyLevel == getDifficultyLevel()) {
//            return
//        }
//        difficultyLevel = newDifficultyLevel
//        prefs.putString(DIFFICULTY_KEY, difficultyLevel.name)
//        prefs.flush()
//    }
//
//    private const val HIGH_SCORE_KEY = "highscore"
//    private const val DIFFICULTY_KEY = "difficulty"
//    private const val PREFS_NAME = "obstacleAvoidPref"
//}