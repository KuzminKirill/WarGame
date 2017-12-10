package com.wardemo.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.Application.ApplicationType
import com.badlogic.gdx.graphics.GL20
import com.wardemo.game.core.Control
import com.wardemo.game.core.WorldController
import com.wardemo.game.core.WorldRenderer

class LevelScreen : Screen, InputProcessor{

    lateinit private var _world : LevelWorld
    lateinit private var _renderer : WorldRenderer
    lateinit private var _controller : WorldController
    lateinit private var _control : Control

    //    score = 0
    //    lastDropTime = 0
    //    coinsCount = 10


    override fun show() {
        _world = LevelWorld()
        _control = Control()
        _renderer = WorldRenderer(_world, _control)
        _controller = WorldController(_world)

        Gdx.input.inputProcessor = this
    }

    override fun touchDragged(x : Int, y :Int, pointer : Int) : Boolean{
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

    override fun touchDown(x : Int, y : Int, pointer : Int, button : Int) : Boolean {
        when {
            _control.leftBtn.isTouched(x.toFloat(),1080 - y.toFloat()) -> { _controller.leftPressed(); _control.x++}
            _control.rightBtn.isTouched(x.toFloat(),1080 - y.toFloat()) -> { _controller.rightPressed(); _control.y++ }
            _control.jumpBtn.isTouched(x.toFloat(),1080 - y.toFloat()) -> {_controller.upPressed(); _control.z++ }
        }
        return true
    }

    override fun touchUp(x : Int, y : Int, pointer : Int, button : Int) : Boolean {
        _controller.resetWay()
        _controller.releaseKeys()
        return true
    }

    override fun resize(width: Int, height: Int) {}
    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {return false}
    override fun keyTyped(character: Char): Boolean {return false}
    override fun scrolled(amount: Int): Boolean {return false}
    override fun keyUp(keycode: Int): Boolean {return false}
    override fun keyDown(keycode: Int): Boolean {return false}
    override fun hide() {}
    override fun pause() {}
    override fun resume() {}

}