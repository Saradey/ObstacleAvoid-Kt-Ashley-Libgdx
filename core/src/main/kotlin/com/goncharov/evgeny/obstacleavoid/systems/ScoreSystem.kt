package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.goncharov.evgeny.obstacleavoid.consts.SCORE_MAX_TIME

class ScoreSystem : IntervalSystem(
    SCORE_MAX_TIME
) {

    override fun updateInterval() {

    }
}