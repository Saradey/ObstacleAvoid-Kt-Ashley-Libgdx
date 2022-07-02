package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

class MovementComponent : Component, Pool.Poolable {
    var xSpeed: Float = 0f
    var ySpeed: Float = 0f

    override fun reset() {
        xSpeed = 0f
        ySpeed = 0f
    }
}