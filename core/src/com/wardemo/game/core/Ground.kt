package com.wardemo.game.core

import com.badlogic.gdx.math.Vector2

class Ground(x : Float, y : Float) {
    var posgr: Vector2

    init {
        posgr = Vector2(x,y)
    }

    fun getPosGrX(): Float {
        return posgr.x
    }

    fun getPosGrY(): Float {
        return posgr.y
    }

}
