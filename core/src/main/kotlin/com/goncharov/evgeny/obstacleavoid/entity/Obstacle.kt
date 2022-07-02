package com.goncharov.evgeny.obstacleavoid.entity

import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.utils.Pool
import com.goncharov.evgeny.obstacleavoid.consts.MEDIUM_OBSTACLE_SPEED
import com.goncharov.evgeny.obstacleavoid.consts.OBSTACLE_BOUNDS_RADIUS
import com.goncharov.evgeny.obstacleavoid.consts.OBSTACLE_SIZE

class Obstacle : GameObjectBase(), Pool.Poolable {

    var ySpeed = MEDIUM_OBSTACLE_SPEED
    var hit = false

    init {
        setSize(OBSTACLE_SIZE, OBSTACLE_SIZE)
        bounds.setRadius(OBSTACLE_BOUNDS_RADIUS)
    }

    fun update() {
        y -= ySpeed
    }

    fun isPlayerColliding(player: Player): Boolean {
        val playerBounds = player.bounds
        val overlaps = Intersector.overlaps(playerBounds, bounds)
        hit = overlaps
        return overlaps
    }

    fun isNotHit() = hit.not()

    override fun reset() {
        hit = false
    }
}