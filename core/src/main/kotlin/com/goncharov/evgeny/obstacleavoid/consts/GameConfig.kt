package com.goncharov.evgeny.obstacleavoid.consts

const val EASY_OBSTACLE_SPEED = 0.1f
const val MEDIUM_OBSTACLE_SPEED = 0.15f
const val HARD_OBSTACLE_SPEED = 0.18f
const val OBSTACLE_BOUNDS_RADIUS = 0.3f
const val OBSTACLE_SIZE = 2 * OBSTACLE_BOUNDS_RADIUS
const val OBSTACLE_SPAWN_TIME = 0.25f

const val LIVES_START = 3

const val PLAYER_BOUNDS_RADIUS = 0.4f
const val PLAYER_SIZE = 2 * PLAYER_BOUNDS_RADIUS
const val START_PLAYER_X = (WORLD_WIDTH - PLAYER_SIZE) / 2f
const val START_PLAYER_Y = 1 - PLAYER_SIZE / 2f
const val MAX_PLAYER_X_SPEED = 0.25f

const val SCORE_MAX_TIME = 1.25f