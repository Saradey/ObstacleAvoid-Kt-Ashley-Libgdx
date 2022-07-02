package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.components.BoundsComponent
import com.goncharov.evgeny.obstacleavoid.components.DimensionComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent

class BoundsSystem : IteratingSystem(
    Family.all(
        BoundsComponent::class.java,
        PositionComponent::class.java,
        DimensionComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {

    }
}