package com.goncharov.evgeny.obstacleavoid.components

import com.badlogic.ashley.core.Component
import com.goncharov.evgeny.obstacleavoid.consts.LIVES_START
import com.goncharov.evgeny.obstacleavoid.managers.models.DifficultyLevel

class GameManagerComponent : Component {
    var gameIsPause = false
    var highScore = 0
    var difficultyLevel = DifficultyLevel.MEDIUM
    var score = 0
    var lives = LIVES_START
    var reset = false
}