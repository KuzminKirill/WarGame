package com.wardemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.wardemo.game.WarDemo

class MenuScreen(game: WarDemo) : Screen, InputProcessor {

    var _game : WarDemo = game
    private var sb : SpriteBatch = SpriteBatch()
    private val background: Texture = Texture("StartScreen.png")
    private val playBtn : customButton = customButton(575f,450f, "PlayBtn.png")
    private val exitBtn : customButton = customButton(1330f,230f,"ExitBtn.png")
    override fun hide() {
        //
    }

    override fun show() {
        //
    Gdx.input.inputProcessor = this
    }

    override fun render(delta: Float) {

        sb.begin()
        sb.draw(background, 0f, 0f, 1920f, 1080f)
        sb.draw(playBtn.button,playBtn.x,playBtn.y)
        sb.draw(exitBtn.button,exitBtn.x,exitBtn.y)
        sb.end()
    }

    override fun pause() {
        //
    }

    override fun resume() {
        //
    }

    override fun resize(width: Int, height: Int) {
        //
    }

    override fun dispose() {
        exitBtn.free()
        playBtn.free()
        background.dispose()
        Gdx.input.inputProcessor = null
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (playBtn.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat()))
            _game.screen = _game.level
        if (exitBtn.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat()))
            Gdx.app.exit()
        return true
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {

        return true
    }
}







/*class MenuScreen(gsm: GameStateManager) : State(gsm) {

    private val background: Texture = Texture("StartScreen.png")
    private val playBtn : customButton = customButton(575f,450f, "PlayBtn.png")
    private val exitBtn : customButton = customButton(1330f,230f,"ExitBtn.png")

    public override fun handleInput() {
        if (playBtn.isTouched(Gdx.input.x.toFloat(), 1080 - Gdx.input.y.toFloat()))
           /// gsm.set(LevelWorld(gsm))
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
}*/