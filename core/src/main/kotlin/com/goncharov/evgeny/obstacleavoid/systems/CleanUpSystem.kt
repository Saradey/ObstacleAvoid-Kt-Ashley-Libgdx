package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.components.CleanUpComponent
import com.goncharov.evgeny.obstacleavoid.components.PositionComponent

class CleanUpSystem : IteratingSystem(
    Family.all(
        CleanUpComponent::class.java,
        PositionComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {

    }
}