package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.MovementComponent
import com.goncharov.evgeny.obstacleavoid.components.PlayerComponent
import com.goncharov.evgeny.obstacleavoid.consts.MAX_PLAYER_X_SPEED

/**
 * Система управления игроком
 */
class PlayerSystem : IteratingSystem(
    Family.all(
        PlayerComponent::class.java,
        MovementComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val movement = Mappers.movement[entity]
        movement.xSpeed = 0f
        when {
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> {
                movement.xSpeed = MAX_PLAYER_X_SPEED
            }
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> {
                movement.xSpeed = -MAX_PLAYER_X_SPEED
            }
        }
    }
}