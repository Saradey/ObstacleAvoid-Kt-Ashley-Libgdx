package com.goncharov.evgeny.obstacleavoid.screens.menu

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.goncharov.evgeny.obstacleavoid.common.BaseStageScreen
import com.goncharov.evgeny.obstacleavoid.common.addListenerKtx
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors
import com.goncharov.evgeny.obstacleavoid.consts.BACKGROUND
import com.goncharov.evgeny.obstacleavoid.consts.PANEL
import com.goncharov.evgeny.obstacleavoid.managers.GameManager
import com.goncharov.evgeny.obstacleavoid.managers.models.DifficultyLevel
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.FpsMonitorManager

class OptionsScreen(
    navigation: Navigation,
    assetManager: AssetManager,
    batch: SpriteBatch,
    fpsMonitorManager: FpsMonitorManager
) : BaseStageScreen(navigation, assetManager, batch, fpsMonitorManager) {

    private var group: ButtonGroup<CheckBox>? = null

    override fun initUi(): Actor {
        val table = Table()
        table.defaults().pad(15f)
        val textureAtlas = assetManager[AssetDescriptors.GAME_PLAY_DESCRIPTOR]
        val textureRegion = textureAtlas.findRegion(BACKGROUND)
        table.background = TextureRegionDrawable(textureRegion)
        val label = Label("DIFFICULTY", uiSkin)
        val easy = createCheckBox(DifficultyLevel.EASY.name)
        easy.addListenerKtx(::difficultyChanged)
        val medium = createCheckBox(DifficultyLevel.MEDIUM.name)
        medium.addListenerKtx(::difficultyChanged)
        val hard = createCheckBox(DifficultyLevel.HARD.name)
        hard.addListenerKtx(::difficultyChanged)
        group = ButtonGroup(easy, medium, hard)
        group?.setChecked(GameManager.getDifficultyLevel().name)
        val backButton = TextButton("BACK", uiSkin)
        backButton.addListenerKtx(::back)
        val contentTable = Table(uiSkin)
        contentTable.defaults().pad(10f)
        contentTable.setBackground(PANEL)
        contentTable.add(label).row()
        contentTable.add(easy).row()
        contentTable.add(medium).row()
        contentTable.add(hard).row()
        contentTable.add(backButton)
        table.add(contentTable)
        table.center()
        table.setFillParent(true)
        table.pack()
        return table
    }

    private fun createCheckBox(text: String): CheckBox {
        val checkBox = CheckBox(text, uiSkin)
        checkBox.name = text
        checkBox.left().pad(8f)
        checkBox.labelCell.pad(8f)
        return checkBox
    }

    private fun back() {
        debug("back")
        navigation.navigate(KeyNavigation.MenuKey)
    }

    private fun difficultyChanged() {
        debug("select difficulty")
        group?.checked ?: return
        val checkBoxTag = group?.checked?.name.orEmpty()
        GameManager.updateDifficulty(DifficultyLevel.valueOf(checkBoxTag))
    }
}