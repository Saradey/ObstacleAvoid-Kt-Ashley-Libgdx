package com.goncharov.evgeny.obstacleavoid.systems

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.SCORE_MAX_TIME
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily

class ScoreSystem : IntervalSystem(
    SCORE_MAX_TIME
) {

    private val gameComponent by lazy {
        Mappers.game[engine.getEntitiesFor(gameManagerFamily).first()]
    }

    override fun updateInterval() {
        if (!gameComponent.gameIsPause && !gameComponent.gameIsOver()) {
            gameComponent.score += MathUtils.random(1, 5)
        }
    }
}