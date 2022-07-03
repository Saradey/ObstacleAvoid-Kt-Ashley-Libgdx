package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.CleanUpComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent
import com.goncharov.evgeny.obstacleavoid.consts.OBSTACLE_SIZE

/**
 * Система удаления объектов если они вышли за пределы экрана
 */
class CleanUpSystem : IteratingSystem(
    Family.all(
        CleanUpComponent::class.java,
        PositionComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = Mappers.position[entity]
        if (position.y < -OBSTACLE_SIZE) {
            engine.removeEntity(entity)
        }
    }
}