package com.goncharov.evgeny.obstacleavoid.navigation

sealed class KeyNavigation {
    object LoadingKey : KeyNavigation()
    object MenuKey : KeyNavigation()
    object GameKey : KeyNavigation()
    object HighScoreKey : KeyNavigation()
    object OptionsKey : KeyNavigation()
}