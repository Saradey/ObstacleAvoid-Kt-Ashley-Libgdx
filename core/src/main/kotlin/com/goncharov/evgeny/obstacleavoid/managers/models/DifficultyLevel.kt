package com.goncharov.evgeny.obstacleavoid.managers.models

import com.goncharov.evgeny.obstacleavoid.consts.EASY_OBSTACLE_SPEED
import com.goncharov.evgeny.obstacleavoid.consts.HARD_OBSTACLE_SPEED
import com.goncharov.evgeny.obstacleavoid.consts.MEDIUM_OBSTACLE_SPEED

enum class DifficultyLevel(val obstacleSpeed: Float) {
    EASY(EASY_OBSTACLE_SPEED),
    MEDIUM(MEDIUM_OBSTACLE_SPEED),
    HARD(HARD_OBSTACLE_SPEED)
}