package com.goncharov.evgeny.obstacleavoid.systems.debug

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_WIDTH
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily

class GridRenderSystem(
    private val gameViewport: Viewport,
    private val renderer: ShapeRenderer
) : EntitySystem() {

    private val debug by lazy {
        Mappers.debug[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    override fun update(deltaTime: Float) {
        if (debug.renderDebug) {
            gameViewport.apply()
            renderer.projectionMatrix = gameViewport.camera.combined
            renderer.begin(ShapeRenderer.ShapeType.Line)
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
        private const val WIDTH_LINE = WORLD_WIDTH / 10f
        private const val HOW_MANY_LINES_X = (WORLD_WIDTH / WIDTH_LINE).toInt()
        private const val HOW_MANY_LINES_Y = (WORLD_HEIGHT / WIDTH_LINE).toInt()
    }
}