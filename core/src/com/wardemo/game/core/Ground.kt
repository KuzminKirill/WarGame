package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2

class Ground {
    private var gr :Texture? = null
    private var posgr: Vector2? = null

    init {
        gr = Texture("ground.png")
    }

    fun position(x: Float, y :Float) {
        posgr = Vector2(x, y)
    }
}