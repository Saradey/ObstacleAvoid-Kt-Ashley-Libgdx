package com.goncharov.evgeny.obstacleavoid.entity

import com.goncharov.evgeny.obstacleavoid.consts.PLAYER_BOUNDS_RADIUS
import com.goncharov.evgeny.obstacleavoid.consts.PLAYER_SIZE


class Player : GameObjectBase() {

    init {
        setSize(PLAYER_SIZE, PLAYER_SIZE)
        bounds.setRadius(PLAYER_BOUNDS_RADIUS)
    }
}