package com.wardemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Application.ApplicationType
import com.badlogic.gdx.graphics.GL20
import com.wardemo.game.core.GameWorld
import com.wardemo.game.core.Player
import com.wardemo.game.core.WorldController
import com.wardemo.game.core.WorldRenderer


class GameScreen() : Screen, InputProcessor{

    lateinit private var _world : LevelWorld
    lateinit private var _renderer : WorldRenderer
    lateinit private var _controller : WorldController

    var width : Int = 0
    var height : Int = 0

    override fun show() {
        _world = LevelWorld()
        _renderer = WorldRenderer(_world, 8f, 5f, true)
        _controller = WorldController(_world)
        Gdx.input.inputProcessor = this
    }

    override fun touchDragged(x : Int, y :Int, pointer : Int) : Boolean{
        ChangeNavigation(x,y)
        return false
    }

    override fun dispose() {
        Gdx.input.inputProcessor = null
    }

    override fun render(delta : Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        _controller.update(delta)
        _renderer.render(delta)
    }

    fun ChangeNavigation(x : Int, y : Int){
      //  _controller.resetWay()
        if(height-y >  _controller.player.position.y * _renderer.ppuY)
            _controller.upPressed()

        if(height-y <  _controller.player.position.y * _renderer.ppuY)
            _controller.downPressed()

        if ( x< _controller.player.position.x * _renderer.ppuX)
            _controller.leftPressed()

        if (x> (_controller.player.position.x + Player.SIZE)* _renderer.ppuX)
            _controller.rightPressed()
    }

    override fun touchDown(x : Int, y : Int, pointer : Int, button : Int) : Boolean {

        if (!Gdx.app.type.equals(ApplicationType.Android))
            return false
        ChangeNavigation(x,y)
        return true
    }

    override fun touchUp(x : Int, y : Int, pointer : Int, button : Int) : Boolean {
        if (!Gdx.app.getType().equals(ApplicationType.Android))
            return false
        _controller.resetWay()
        return true
    }

    override fun resize(width: Int, height: Int) {
        _renderer.setSize(width, height)
        this.width = width
        this.height = height
    }


    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {return false}
    override fun keyTyped(character: Char): Boolean {return false}
    override fun scrolled(amount: Int): Boolean {return false}
    override fun keyUp(keycode: Int): Boolean {return false}
    override fun keyDown(keycode: Int): Boolean {return false}
    override fun hide() {}
    override fun pause() {}
    override fun resume() {}

}