package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily

class UiRenderSystem(
    private val font: BitmapFont,
    private val uiViewport: Viewport,
    private val batch: SpriteBatch
) : EntitySystem() {

    private val layout = GlyphLayout()

    private val gm by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    override fun update(deltaTime: Float) {
        uiViewport.apply()
        batch.projectionMatrix = uiViewport.camera.combined
        batch.begin()
        draw()
        batch.end()
    }

    private fun draw() {
        val liveText = LIVE_TEXT.format(gm.lives)
        layout.setText(font, liveText)
        font.color = Color.WHITE
        font.draw(batch, liveText, 20f, UI_HEIGHT - layout.height)
        val scoreText = SCORE_TEXT.format(gm.score)
        layout.setText(font, scoreText)
        font.draw(batch, scoreText, UI_WIDTH - layout.width - 20f, UI_HEIGHT - layout.height)
        if (gm.gameIsOver()) {
            layout.setText(font, OVER_GAME_TEXT)
            font.color = Color.RED
            font.draw(
                batch,
                OVER_GAME_TEXT,
                (UI_WIDTH - layout.width) / 2f,
                (UI_HEIGHT - layout.height) / 2f
            )
        }
    }

    companion object {
        private const val LIVE_TEXT = "LIVES: %d"
        private const val SCORE_TEXT = "SCORE: %d"
        private const val OVER_GAME_TEXT = "GAME OVER"
    }
}