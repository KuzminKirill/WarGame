package com.wardemo.game

import com.badlogic.gdx.Game
import com.wardemo.game.states.LevelScreen
import com.wardemo.game.states.MenuScreen


class WarDemo : Game() {

    lateinit var menu : MenuScreen
    lateinit var level : LevelScreen
    override fun create() {
        menu = MenuScreen(this)
        level = LevelScreen()
        setScreen(menu)
    }
}



/*class WarDemo : ApplicationAdapter() {

    private var gsm: GameStateManager? = null
    private var batch: SpriteBatch? = null

    override fun create() {
        batch = SpriteBatch()
        gsm = GameStateManager()
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        gsm!!.push(MenuScreen(gsm!!))
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gsm!!.update(Gdx.graphics.deltaTime)
        gsm!!.render(batch!!)
    }

    companion object {
        val WIDTH = 1000
        val HEIGHT = 1000

        val TITLE = "War Game"
    }
}*/