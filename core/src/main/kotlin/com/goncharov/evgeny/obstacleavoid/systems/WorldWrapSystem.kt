package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.DimensionComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent
import com.goncharov.evgeny.obstacleavoid.components.WorldWrapComponent

class WorldWrapSystem(
    private val viewport: Viewport
) : IteratingSystem(
    Family.all(
        WorldWrapComponent::class.java,
        PositionComponent::class.java,
        DimensionComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = Mappers.position[entity]
        val dimension = Mappers.dimension[entity]
        position.x = MathUtils.clamp(position.x, 0f, viewport.worldWidth - dimension.width)
        position.y = MathUtils.clamp(position.y, 0f, viewport.worldHeight - dimension.height)
    }
}