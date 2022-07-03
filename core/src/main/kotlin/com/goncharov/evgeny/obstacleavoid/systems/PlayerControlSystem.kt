package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.MovementComponent
import com.goncharov.evgeny.obstacleavoid.components.PlayerComponent
import com.goncharov.evgeny.obstacleavoid.consts.MAX_PLAYER_X_SPEED
import com.goncharov.evgeny.obstacleavoid.consts.WORLD_WIDTH
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily
import com.goncharov.evgeny.obstacleavoid.managers.GameManager

/**
 * Система управления игроком
 */
class PlayerControlSystem(
    private val gameViewport: Viewport
) : IteratingSystem(
    Family.all(
        PlayerComponent::class.java,
        MovementComponent::class.java
    ).get()
) {
    private val gameComponent by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val movement = Mappers.movement[entity]
        movement.xSpeed = 0f
        if (!gameComponent.gameIsPause && !gameComponent.gameIsOver()) {
            when {
                Gdx.input.isKeyPressed(Input.Keys.A) -> {
                    movement.xSpeed = -MAX_PLAYER_X_SPEED
                }
                Gdx.input.isKeyPressed(Input.Keys.D) -> {
                    movement.xSpeed = MAX_PLAYER_X_SPEED
                }
                Gdx.input.isTouched && !GameManager.isGameOver() -> {
                    val worldTouch = gameViewport.unproject(
                        Vector2(
                            Gdx.input.x.toFloat(),
                            Gdx.input.y.toFloat()
                        )
                    )
                    val position = Mappers.position[entity]
                    val dimension = Mappers.dimension[entity]
                    position.x = MathUtils.clamp(worldTouch.x, 0f, WORLD_WIDTH - dimension.width)
                }
            }
        }
    }
}