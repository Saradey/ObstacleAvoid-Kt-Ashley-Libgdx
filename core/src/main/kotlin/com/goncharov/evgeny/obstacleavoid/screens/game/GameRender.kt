package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.consts.*
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.FONT_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.GAME_PLAY_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils
import com.goncharov.evgeny.obstacleavoid.util.debug.DebugCameraController

class GameRender(
    private val renderer: ShapeRenderer,
    assetManager: AssetManager,
    private val gameController: GameController,
    private val batch: SpriteBatch
) {
    private val camera = OrthographicCamera()
    private val viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
    private val uiCamera = OrthographicCamera()
    private val uiViewport = FitViewport(UI_WIDTH, UI_HEIGHT, uiCamera)
    private val font = assetManager[FONT_DESCRIPTOR]
    private val layout = GlyphLayout()
    private val gamePlayAtlas = assetManager[GAME_PLAY_DESCRIPTOR]
    private val playerRegion = gamePlayAtlas.findRegion(PLAYER)
    private val obstacleRegion = gamePlayAtlas.findRegion(OBSTACLE)
    private val backgroundRegion = gamePlayAtlas.findRegion(BACKGROUND)

    fun render() {
        DebugCameraController.updateCamera(camera)
        touchedUpdate()
        GdxUtils.clearScreen()
        renderGamePlay()
        renderUi()
        renderDebug()
    }

    fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        uiViewport.update(width, height, true)
    }

    private fun touchedUpdate() {
        if (Gdx.input.isTouched && !gameController.isGameOver() && !gameController.gameIsPause) {
            val worldTouch =
                viewport.unproject(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))
            gameController.player.x =
                MathUtils.clamp(worldTouch.x, 0f, WORLD_WIDTH - gameController.player.width)
        }
    }

    private fun renderGamePlay() {
        viewport.apply()
        if (gameController.drawingSprite) {
            batch.projectionMatrix = camera.combined
            batch.begin()
            val background = gameController.background
            batch.draw(
                backgroundRegion,
                background.x,
                background.y,
                background.width,
                background.height
            )
            val player = gameController.player
            batch.draw(playerRegion, player.x, player.y, player.width, player.height)
            gameController.obstacles.forEach { obstacle ->
                batch.draw(obstacleRegion, obstacle.x, obstacle.y, obstacle.width, obstacle.height)
            }
            batch.end()
        }
    }

    private fun renderUi() {
        uiViewport.apply()
        batch.projectionMatrix = uiCamera.combined
        batch.begin()
        val liveText = LIVE_TEXT.format(gameController.lives)
        layout.setText(font, liveText)
        font.color = Color.WHITE
        font.draw(batch, liveText, 20f, UI_HEIGHT - layout.height)
        val scoreText = SCORE_TEXT.format(gameController.displayScore)
        layout.setText(font, scoreText)
        font.draw(batch, scoreText, UI_WIDTH - layout.width - 20f, UI_HEIGHT - layout.height)
        if (gameController.isGameOver()) {
            layout.setText(font, OVER_GAME_TEXT)
            font.color = Color.RED
            font.draw(
                batch,
                OVER_GAME_TEXT,
                (UI_WIDTH - layout.width) / 2f,
                (UI_HEIGHT - layout.height) / 2f
            )
        }
        batch.end()
    }

    private fun renderDebug() {
        if (gameController.debugRender) {
            renderer.color = Color.RED
            viewport.apply()
            renderer.projectionMatrix = camera.combined
            renderer.begin(ShapeRenderer.ShapeType.Line)
            gameController.player.drawDebug(renderer)
            gameController.obstacles.forEach { obstacle ->
                obstacle.drawDebug(renderer)
            }
            renderer.color = Color.WHITE
            for (line in 0..HOW_MANY_LINES_X) {
                renderer.line(
                    Vector2(line.toFloat() * WIDTH_LINE, 0f),
                    Vector2(line.toFloat() * WIDTH_LINE, WORLD_HEIGHT)
                )
            }
            for (line in 0..HOW_MANY_LINES_Y) {
                renderer.line(
                    Vector2(0f, line.toFloat() * WIDTH_LINE),
                    Vector2(WORLD_WIDTH, line.toFloat() * WIDTH_LINE)
                )
            }
            renderer.end()
        }
    }

    companion object {
        private const val LIVE_TEXT = "LIVES: %d"
        private const val SCORE_TEXT = "SCORE: %d"
        private const val OVER_GAME_TEXT = "GAME OVER"
        private const val WIDTH_LINE = WORLD_WIDTH / 10f
        private const val HOW_MANY_LINES_X = (WORLD_WIDTH / WIDTH_LINE).toInt()
        private const val HOW_MANY_LINES_Y = (WORLD_HEIGHT / WIDTH_LINE).toInt()
    }
}