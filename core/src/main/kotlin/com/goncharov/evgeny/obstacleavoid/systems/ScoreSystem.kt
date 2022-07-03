package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.goncharov.evgeny.obstacleavoid.consts.SCORE_MAX_TIME
import com.goncharov.evgeny.obstacleavoid.managers.GameManager

class ScoreSystem : IntervalSystem(
    SCORE_MAX_TIME
) {

    override fun updateInterval() {
        GameManager.updateScore(MathUtils.random(1, 5))
    }
}