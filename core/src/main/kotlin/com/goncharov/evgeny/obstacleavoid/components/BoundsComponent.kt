package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Circle

class BoundsComponent : Component {
    val bounds: Circle = Circle()
}