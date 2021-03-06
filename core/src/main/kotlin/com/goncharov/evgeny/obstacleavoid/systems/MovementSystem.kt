package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.MovementComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily

/**
 * Система движения объектов
 */
class MovementSystem : IteratingSystem(
    Family.all(
        PositionComponent::class.java,
        MovementComponent::class.java
    ).get()
) {

    private val gameComponent by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        if (!gameComponent.gameIsPause && !gameComponent.gameIsOver()) {
            val position = Mappers.position[entity]
            val movement = Mappers.movement[entity]
            position.x += movement.xSpeed
            position.y += movement.ySpeed
        }
    }
}