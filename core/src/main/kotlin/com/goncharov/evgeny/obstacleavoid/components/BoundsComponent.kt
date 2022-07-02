package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Circle

data class BoundsComponent(val bounds: Circle) : Component