package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.DimensionComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent
import com.goncharov.evgeny.obstacleavoid.components.TextureComponent

class RenderSystem(
    private val gameViewport: Viewport,
    private val batch: SpriteBatch
) : EntitySystem() {

    private val family = Family.all(
        TextureComponent::class.java,
        PositionComponent::class.java,
        DimensionComponent::class.java
    ).get()

    override fun update(deltaTime: Float) {
        gameViewport.apply()
        batch.projectionMatrix = gameViewport.camera.combined
        batch.begin()
        draw(engine.getEntitiesFor(family))
        batch.end()
    }

    private fun draw(entities: ImmutableArray<Entity>) {
        entities.forEach { model ->
            val position = Mappers.position[model]
            val dimension = Mappers.dimension[model]
            val texture = Mappers.texture[model]
            batch.draw(
                texture.textureRegion,
                position.x,
                position.y,
                dimension.width,
                dimension.height
            )
        }
    }
}