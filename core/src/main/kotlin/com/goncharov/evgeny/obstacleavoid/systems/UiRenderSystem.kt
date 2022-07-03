package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.managers.GameManager

class UiRenderSystem(
    private val font: BitmapFont,
    private val uiViewport: Viewport,
    private val batch: SpriteBatch
) : EntitySystem() {

    private val layout = GlyphLayout()

    override fun update(deltaTime: Float) {
        uiViewport.apply()
        batch.projectionMatrix = uiViewport.camera.combined
        batch.begin()
        draw()
        batch.end()
    }

    private fun draw() {
        val liveText = LIVE_TEXT.format(GameManager.lives)
        layout.setText(font, liveText)
        font.color = Color.WHITE
        font.draw(batch, liveText, 20f, UI_HEIGHT - layout.height)
        val scoreText = SCORE_TEXT.format(GameManager.score)
        layout.setText(font, scoreText)
        font.draw(batch, scoreText, UI_WIDTH - layout.width - 20f, UI_HEIGHT - layout.height)
    }

    companion object {
        private const val LIVE_TEXT = "LIVES: %d"
        private const val SCORE_TEXT = "SCORE: %d"
    }
}