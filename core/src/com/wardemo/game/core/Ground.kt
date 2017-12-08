package com.wardemo.game.core

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Ground(x : Float, y : Float) {
    var gr :Texture
    var posgr: Vector2
    var rec : Rectangle
    var center :Vector2

    init {
        gr = Texture("Dirt.png")
        posgr = Vector2(x,y)
        rec = Rectangle(x,y,gr.width.toFloat(),gr.height.toFloat())
        center = Vector2(x + (0.5 * rec.width).toFloat(),y + (0.5 * rec.height).toFloat())
    }

    fun getPosGrX(): Float {
        return posgr.x
    }

    fun getPosGrY(): Float {
        return posgr.y
    }

    fun getGrTexture(): Texture {
        return gr
    }

    fun free(){
        gr.dispose()
    }
}
