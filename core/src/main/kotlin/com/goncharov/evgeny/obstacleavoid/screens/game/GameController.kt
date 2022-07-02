package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pools
import com.goncharov.evgeny.obstacleavoid.common.BaseInputProcessor
import com.goncharov.evgeny.obstacleavoid.consts.*
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.HIT_SOUND_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.entity.Background
import com.goncharov.evgeny.obstacleavoid.entity.Obstacle
import com.goncharov.evgeny.obstacleavoid.entity.Player
import com.goncharov.evgeny.obstacleavoid.managers.GameManager
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.FpsMonitorManager
import com.goncharov.evgeny.obstacleavoid.util.LogDebugUtils.debug
import kotlin.math.min

class GameController(
    assetManager: AssetManager,
    private val navigation: Navigation,
    private val fpsMonitorManager: FpsMonitorManager
) : BaseInputProcessor() {
    val player = Player()
    val background = Background()
    val obstacles = Array<Obstacle>()
    private var obstacleTimer = 0f
    private var scoreTimer = 0f
    var lives = LIVES_START
    private var score = 0
    var displayScore = 0
    private val obstaclePool = Pools.get(Obstacle::class.java, 40)
    private val hit = assetManager[HIT_SOUND_DESCRIPTOR]
    var gameIsPause = false
    var debugRender = false
    var drawingSprite = true
    private var isOverTime = 0f

    init {
        player.setPosition(START_PLAYER_X, START_PLAYER_Y)
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.SPACE -> gameIsPause = !gameIsPause
            Input.Keys.C -> debugRender = !debugRender
            Input.Keys.V -> drawingSprite = !drawingSprite
            Input.Keys.B -> fpsMonitorManager.updateFpsMonitor()
        }
        return true
    }

    fun update(delta: Float) {
        if (isGameOver()) {
            isOverTime += delta
            if (isOverTime > 2f) {
                navigation.navigate(KeyNavigation.MenuKey)
            }
        }
        if (gameIsPause || isGameOver()) return
        updatePlayer()
        updateObstacles(delta)
        updateScore(delta)
        updateDisplayScore(delta)
        updateGameRules()
    }

    private fun updatePlayer() {
        var playerX = player.x
        when {
            Gdx.input.isKeyPressed(Input.Keys.A) -> {
                playerX -= MAX_PLAYER_X_SPEED
            }
            Gdx.input.isKeyPressed(Input.Keys.D) -> {
                playerX += MAX_PLAYER_X_SPEED
            }
        }
        player.x = MathUtils.clamp(playerX, 0f, WORLD_WIDTH - player.width)
    }

    private fun updateObstacles(delta: Float) {
        obstacles.forEach { obstacle ->
            obstacle.update()
        }
        obstacleTimer += delta
        if (obstacleTimer >= OBSTACLE_SPAWN_TIME) {
            val min = 0f
            val max = WORLD_WIDTH - OBSTACLE_SIZE
            val obstacleX = MathUtils.random(min, max)
            val obstacleY = WORLD_HEIGHT
            val obstacle = obstaclePool.obtain()
            val difficultyLevel = GameManager.getDifficultyLevel()
            obstacle.ySpeed = difficultyLevel.obstacleSpeed
            obstacle.setPosition(obstacleX, obstacleY)
            obstacles.add(obstacle)
            obstacleTimer = 0f
        }
        if (obstacles.size > 0) {
            val first = obstacles.first()
            if (first.y < -OBSTACLE_SIZE) {
                obstacles.removeValue(first, true)
                obstaclePool.free(first)
            }
        }
    }

    private fun updateScore(delta: Float) {
        scoreTimer += delta
        if (scoreTimer > SCORE_MAX_TIME) {
            score += MathUtils.random(1, 5)
            scoreTimer = 0.0f
        }
    }

    private fun updateDisplayScore(delta: Float) {
        if (displayScore < score) {
            displayScore = min(score, displayScore + (60 * delta).toInt())
        }
    }

    private fun updateGameRules() {
        if (isPlayerCollidingWithObstacle()) {
            debug("GameController", "Collision detected")
            lives--
            if (isGameOver()) {
                debug("GameController", "Game Over!!!")
                GameManager.updateHighScore(score)
            } else {
                restart()
            }
        }
    }

    private fun isPlayerCollidingWithObstacle(): Boolean {
        obstacles.forEach { obstacle ->
            if (obstacle.isNotHit() && obstacle.isPlayerColliding(player)) {
                hit.play()
                return true
            }
        }
        return false
    }

    private fun restart() {
        obstaclePool.freeAll(obstacles)
        obstacles.clear()
        player.setPosition(START_PLAYER_X, START_PLAYER_Y)
    }

    fun isGameOver() = lives <= 0
}