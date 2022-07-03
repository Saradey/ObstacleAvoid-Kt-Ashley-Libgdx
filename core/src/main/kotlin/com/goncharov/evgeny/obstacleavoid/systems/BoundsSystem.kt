package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.BoundsComponent
import com.goncharov.evgeny.obstacleavoid.components.DimensionComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent

/**
 * Система обновления границ объектов на карте
 */
class BoundsSystem : IteratingSystem(
    Family.all(
        BoundsComponent::class.java,
        PositionComponent::class.java,
        DimensionComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bounds = Mappers.bounds[entity]
        val position = Mappers.position[entity]
        val dimension = Mappers.dimension[entity]
        bounds.bounds.x = position.x + dimension.width / 2f
        bounds.bounds.y = position.y + dimension.height / 2f
    }
}