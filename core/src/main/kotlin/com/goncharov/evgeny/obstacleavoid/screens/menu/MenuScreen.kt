package com.goncharov.evgeny.obstacleavoid.screens.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.goncharov.evgeny.obstacleavoid.common.BaseStageScreen
import com.goncharov.evgeny.obstacleavoid.common.addListenerKtx
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors
import com.goncharov.evgeny.obstacleavoid.consts.BACKGROUND
import com.goncharov.evgeny.obstacleavoid.consts.PANEL
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.FpsMonitorManager

class MenuScreen(
    navigation: Navigation,
    assetManager: AssetManager,
    batch: SpriteBatch,
    fpsMonitorManager: FpsMonitorManager
) : BaseStageScreen(navigation, assetManager, batch, fpsMonitorManager) {

    override fun initUi(): Actor {
        val table = Table()
        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_PLAY_DESCRIPTOR]
        table.background = TextureRegionDrawable(gamePlayAtlas.findRegion(BACKGROUND))

        val playButton = TextButton("PLAY", uiSkin)
        playButton.addListenerKtx(::play)

        val highScoreButton = TextButton("HIGHSCORE", uiSkin)
        highScoreButton.addListenerKtx(::showHighScore)

        val optionsButton = TextButton("OPTIONS", uiSkin)
        optionsButton.addListenerKtx(::showOptions)

        val quitButton = TextButton("QUIT", uiSkin)
        quitButton.addListenerKtx(::quit)

        val buttonTable = Table(uiSkin)
        buttonTable.defaults().pad(20f)
        buttonTable.setBackground(PANEL)
        buttonTable.add(playButton).row()
        buttonTable.add(highScoreButton).row()
        buttonTable.add(optionsButton).row()
        buttonTable.add(quitButton)
        buttonTable.center()

        table.add(buttonTable)
        table.center()
        table.setFillParent(true)
        table.pack()
        return table
    }

    private fun play() {
        debug("click play button")
        navigation.navigate(KeyNavigation.GameKey)
    }

    private fun showHighScore() {
        debug("click highScore button")
        navigation.navigate(KeyNavigation.HighScoreKey)
    }

    private fun showOptions() {
        debug("click options button")
        navigation.navigate(KeyNavigation.OptionsKey)
    }

    private fun quit() {
        debug("click quit button")
        Gdx.app.exit()
    }
}