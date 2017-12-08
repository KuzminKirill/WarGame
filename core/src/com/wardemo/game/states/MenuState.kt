package com.wardemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture

class MenuState(gsm: GameStateManager) : State(gsm) {

    private val background: Texture = Texture("StartScreen.png")
    private val playBtn : customButton = customButton(575f,450f, "PlayBtn.png")
    private val exitBtn : customButton = customButton(1330f,230f,"ExitBtn.png")

    public override fun handleInput() {
        if (playBtn.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat()))
            gsm.set(LevelWorld(gsm))
        if (exitBtn.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat())) {
            Gdx.app.exit()
        }
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun render(sb: SpriteBatch) {
        sb.begin()
        sb.draw(background, 0f, 0f, 1920f, 1080f)
        sb.draw(playBtn.button,playBtn.x,playBtn.y)
        sb.draw(exitBtn.button,exitBtn.x,exitBtn.y)
        sb.end()
    }

    override fun dispose() {
        exitBtn.free()
        playBtn.free()
        background.dispose()
    }
}