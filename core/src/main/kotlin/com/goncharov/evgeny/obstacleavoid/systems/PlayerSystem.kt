package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.goncharov.evgeny.obstacleavoid.components.MovementComponent
import com.goncharov.evgeny.obstacleavoid.components.PlayerComponent

class PlayerSystem : IteratingSystem(
    Family.all(
        PlayerComponent::class.java,
        MovementComponent::class.java
    ).get()
) {

    override fun processEntity(entity: Entity, deltaTime: Float) {

    }
}