package com.goncharov.evgeny.obstacleavoid.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle

abstract class GameObjectBase {
    var width: Float = 0f
    var height: Float = 0f
    var x: Float = 0f
        set(value) {
            field = value
            updatePosition()
        }
    var y: Float = 0f
        set(value) {
            field = value
            updatePosition()
        }
    val bounds: Circle = Circle()

    fun drawDebug(renderer: ShapeRenderer) {
        renderer.x(bounds.x, bounds.y, 0.1f)
        renderer.circle(bounds.x, bounds.y, bounds.radius, 30)
    }

    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
        updatePosition()
    }

    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
        updatePosition()
    }

    fun updatePosition() {
        val halfWidth = width / 2f
        val halfHeight = height / 2f
        bounds.setPosition(x + halfWidth, y + halfHeight)
    }
}