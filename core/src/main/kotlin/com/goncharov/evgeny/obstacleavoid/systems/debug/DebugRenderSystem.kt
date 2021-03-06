package com.goncharov.evgeny.obstacleavoid.systems.debug

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.BoundsComponent
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily

/**
 * Система отрисовки bounds всех игровых объектов
 */
class DebugRenderSystem(
    private val gameViewport: Viewport,
    private val renderer: ShapeRenderer
) : IteratingSystem(
    Family.all(BoundsComponent::class.java).get()
) {

    private val debugComponent by lazy {
        Mappers.debug[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    override fun update(deltaTime: Float) {
        if (debugComponent.renderDebug) {
            gameViewport.apply()
            renderer.projectionMatrix = gameViewport.camera.combined
            renderer.begin(ShapeRenderer.ShapeType.Line)
            renderer.color = Color.RED
            super.update(deltaTime)
            renderer.end()
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = Mappers.bounds[entity]
        renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius, 30)
    }
}