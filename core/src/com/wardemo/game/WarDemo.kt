package com.wardemo.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wardemo.game.states.MenuState
import com.wardemo.game.states.GameStateManager


class WarDemo : ApplicationAdapter() {

    private var gsm: GameStateManager? = null
    private var batch: SpriteBatch? = null

    override fun create() {
        batch = SpriteBatch()
        gsm = GameStateManager()
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        gsm!!.push(MenuState(gsm!!))
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
}