package com.goncharov.evgeny.obstacleavoid.consts

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

object AssetDescriptors {
    val FONT_DESCRIPTOR = AssetDescriptor(UI_FONT, BitmapFont::class.java)
    val GAME_PLAY_DESCRIPTOR = AssetDescriptor(GAME_PLAY, TextureAtlas::class.java)
    val UI_SKIN_DESCRIPTOR = AssetDescriptor(UI_SKIN, Skin::class.java)
    val HIT_SOUND_DESCRIPTOR = AssetDescriptor(SOUND_HIT, Sound::class.java)
    val FPS_FONT_DESCRIPTOR = AssetDescriptor(UI_FPS_FONT, BitmapFont::class.java)
}
