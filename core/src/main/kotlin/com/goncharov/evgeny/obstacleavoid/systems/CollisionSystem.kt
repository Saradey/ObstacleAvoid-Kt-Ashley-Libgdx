package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.gdx.assets.AssetManager
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.BoundsComponent
import com.goncharov.evgeny.obstacleavoid.components.ObstacleComponent
import com.goncharov.evgeny.obstacleavoid.components.PlayerComponent
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily

/**
 * Система проверки столкновений
 */
class CollisionSystem(
    assetManager: AssetManager
) : EntitySystem() {

    private val playerFamily = Family.all(
        PlayerComponent::class.java,
        BoundsComponent::class.java
    ).get()

    private val obstacleFamily = Family.all(
        ObstacleComponent::class.java,
        BoundsComponent::class.java
    ).get()

    private val gameComponent by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    private val sound = assetManager[AssetDescriptors.HIT_SOUND_DESCRIPTOR]

    override fun update(deltaTime: Float) {
        if (!gameComponent.gameIsPause && !gameComponent.gameIsOver()) {
            val players = engine.getEntitiesFor(playerFamily).first()
            val obstacles = engine.getEntitiesFor(obstacleFamily)
            obstacles.forEach { obstacle ->
                val component = Mappers.obstacle[obstacle]
                if (component.hit) {
                    return@forEach
                }
                if (checkCollision(players, obstacle)) {
                    isHit()
                }
            }
        }
    }

    private fun isHit() {
        gameComponent.lives--
        gameComponent.reset = true
        sound.play()
    }

    private fun checkCollision(player: Entity, obstacle: Entity): Boolean {
        val playerBounds = Mappers.bounds[player]
        val obstacleBounds = Mappers.bounds[obstacle]
        return playerBounds.bounds.overlaps(obstacleBounds.bounds)
    }
}