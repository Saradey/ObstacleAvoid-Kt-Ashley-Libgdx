package com.goncharov.evgeny.obstacleavoid.screens.menu

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.goncharov.evgeny.obstacleavoid.common.BaseStageScreen
import com.goncharov.evgeny.obstacleavoid.common.addListenerKtx
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.GAME_PLAY_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.BACKGROUND
import com.goncharov.evgeny.obstacleavoid.consts.PANEL
import com.goncharov.evgeny.obstacleavoid.managers.GameManager
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.FpsMonitorManager

class HighScoreScreen(
    navigation: Navigation,
    assetManager: AssetManager,
    batch: SpriteBatch,
    fpsMonitorManager: FpsMonitorManager
) : BaseStageScreen(navigation, assetManager, batch, fpsMonitorManager) {

    override fun initUi(): Actor {
        val table = Table()
        val gamePlayAtlas = assetManager[GAME_PLAY_DESCRIPTOR]
        val textureRegion = gamePlayAtlas.findRegion(BACKGROUND)
        table.background = TextureRegionDrawable(textureRegion)

        val highScore = GameManager.getHighScore().toString()
        val highScoreTitle = Label("HIGHSCORE", uiSkin)
        val labelScoreResult = Label(highScore, uiSkin)

        val backButton = TextButton("BACK", uiSkin)
        backButton.addListenerKtx(::back)

        val contentTable = Table(uiSkin)
        contentTable.defaults().pad(20f)
        contentTable.setBackground(PANEL)
        contentTable.add(highScoreTitle).row()
        contentTable.add(labelScoreResult).row()
        contentTable.add(backButton)
        contentTable.center()

        table.add(contentTable)
        table.center()
        table.setFillParent(true)
        table.pack()
        return table
    }

    private fun back() {
        debug("back")
        navigation.navigate(KeyNavigation.MenuKey)
    }
}