package com.goncharov.evgeny.obstacleavoid.managers

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.goncharov.evgeny.obstacleavoid.common.Mappers
import com.goncharov.evgeny.obstacleavoid.consts.DIFFICULTY_KEY
import com.goncharov.evgeny.obstacleavoid.consts.PREFS_NAME
import com.goncharov.evgeny.obstacleavoid.consts.gameManagerFamily
import com.goncharov.evgeny.obstacleavoid.models.DifficultyLevel

class SavedManagers(private val engine: Engine) {

    private val prefs: Preferences by lazy {
        Gdx.app.getPreferences(PREFS_NAME)
    }

    fun saveDifficultyLevel() {
        val gameEntity = engine.getEntitiesFor(gameManagerFamily).first()
        val gameComponent = Mappers.game[gameEntity]
        val oldDifficultyLevel = DifficultyLevel.valueOf(
            prefs.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name)
        )
        if (oldDifficultyLevel == gameComponent.difficultyLevel) {
            return
        }
        prefs.putString(DIFFICULTY_KEY, gameComponent.difficultyLevel.name)
        prefs.flush()
    }

    fun savedHeightScore() {

    }
}