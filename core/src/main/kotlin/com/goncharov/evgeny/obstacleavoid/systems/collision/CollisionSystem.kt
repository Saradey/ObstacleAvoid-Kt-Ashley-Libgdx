package com.goncharov.evgeny.obstacleavoid.systems.collision

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.components.BoundsComponent
import com.goncharov.evgeny.obstacleavoid.components.ObstacleComponent
import com.goncharov.evgeny.obstacleavoid.components.PlayerComponent

class CollisionSystem(
    private val listener: CollisionListener
) : EntitySystem() {

    private val playerFamily = Family.all(
        PlayerComponent::class.java,
        BoundsComponent::class.java
    ).get()

    private val obstacleFamily = Family.all(
        ObstacleComponent::class.java,
        BoundsComponent::class.java
    ).get()

    override fun update(deltaTime: Float) {
        val players = engine.getEntitiesFor(playerFamily).first()
        val obstacles = engine.getEntitiesFor(obstacleFamily)
        obstacles.forEach { obstacle ->
            val component = Mappers.obstacle[obstacle]
            if (component.hit) {
                return@forEach
            }
            if (checkCollision(players, obstacle)) {
                listener.hitObstacle()
            }
        }
    }

    private fun checkCollision(player: Entity, obstacle: Entity): Boolean {
        val playerBounds = Mappers.bounds[player]
        val obstacleBounds = Mappers.bounds[obstacle]
        return playerBounds.bounds.overlaps(obstacleBounds.bounds)
    }
}