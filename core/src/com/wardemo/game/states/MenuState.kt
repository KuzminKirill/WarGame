package com.wardemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch

import com.badlogic.gdx.graphics.Texture

class MenuState(gsm: GameStateManager) : State(gsm) {

    private val background: Texture
    private val playBtn :customButton

    init {
        background = Texture("bliss3.jpg")
        playBtn = customButton(960f,540f, "start.PNG")
    }

    public override fun handleInput() {
        if (playBtn.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat()))
            gsm.set(LevelState(gsm))

    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(background, 0f, 0f, 1920f, 1080f)
        sb.draw(playBtn.button,playBtn.xCor,playBtn.yCor)
        sb.end()
    }

    override fun dispose() {
        playBtn.free()
        background.dispose()
    }
}