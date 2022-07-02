package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

data class MovementComponent(
    var xSpeed: Float,
    var ySpeed: Float
) : Component, Pool.Poolable {

    override fun reset() {
        xSpeed = 0f
        ySpeed = 0f
    }
}