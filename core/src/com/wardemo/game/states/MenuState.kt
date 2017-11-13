package com.wardemo.game.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch

import com.badlogic.gdx.graphics.Texture

class MenuState(gsm: GameStateManager) : State(gsm) {

    private val background: Texture
    private val playBtn: Texture

    init {
        background = Texture("fon.jpg")
        playBtn = Texture("start.PNG")
    }

    public override fun handleInput() {

    }

    override fun update(dt: Float) {

    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(background, 0f, 0f, 1920f, 1080f)
        sb.draw(playBtn, 1920f / 2 - playBtn.width/2, 1080 / 2f)
        sb.end()

    }

    override fun dispose() {
        background.dispose()
        playBtn.dispose()

    }
}