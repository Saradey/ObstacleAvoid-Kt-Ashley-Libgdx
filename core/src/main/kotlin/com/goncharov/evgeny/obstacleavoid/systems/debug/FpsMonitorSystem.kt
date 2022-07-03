package com.goncharov.evgeny.obstacleavoid.systems.debug

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH

class FpsMonitorSystem(
    private val batch: SpriteBatch,
    private val fpsFont: BitmapFont,
    private val uiViewport: Viewport
) : EntitySystem() {

    override fun update(deltaTime: Float) {
        when {
            Gdx.graphics.displayMode.refreshRate / 3 > Gdx.graphics.framesPerSecond -> {
                fpsFont.color = Color.RED
            }
            Gdx.graphics.displayMode.refreshRate / 1.5 > Gdx.graphics.framesPerSecond -> {
                fpsFont.color = Color.YELLOW
            }
            else -> {
                fpsFont.color = Color.GREEN
            }
        }
        uiViewport.apply()
        batch.projectionMatrix = uiViewport.camera.combined
        batch.begin()
        fpsFont.draw(
            batch,
            FPS.format(Gdx.graphics.framesPerSecond),
            UI_WIDTH - 110f,
            UI_HEIGHT - 40f
        )
        batch.end()
    }

    companion object {
        private const val FPS = "FPS:%d"
    }
}