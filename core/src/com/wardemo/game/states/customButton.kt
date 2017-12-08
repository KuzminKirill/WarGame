package com.wardemo.game.states

import com.badlogic.gdx.graphics.Texture

class customButton(ldX : Float, ldY : Float, view : String) {

    var button : Texture = Texture(view)
    var x: Float = ldX
    var y: Float = ldY

    fun isTouched(xCor: Float, yCor: Float) : Boolean {
        return xCor - this.x >= 0.01 && this.x + button.width - xCor >= 0.01
                && yCor - this.y >= 0.01 && this.y + button.height - yCor >= 0.01
    }

    fun free() {
        button.dispose()
    }
}