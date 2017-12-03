package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2

class Ground {
    private var gr :Texture
    private var posgr: Vector2

    init {
        gr = Texture("Dirt.png")
        posgr = Vector2(0f,0f)
    }

    fun getposgrx(): Float {
        return posgr.x
    }

    fun getposgry(): Float {
        return posgr.y
    }

    //fun position(x: Float, y :Float) {
    //    posgr.set(x, y)
    //}

    fun getgr(): Texture {
        return gr
    }
}