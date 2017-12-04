package com.wardemo.game.states

import com.badlogic.gdx.graphics.Texture

class customButton(ldX : Float, ldY : Float, view : String) {

    var button : Texture
    var xCor : Float
    var yCor : Float
    //var rec : Rectangle
    init {
      //  rec = Rectangle(ldX, ldY, width, height)
        button = Texture(view)
        xCor = ldX
        yCor = ldY
    }

    fun isTouched(x : Float, y : Float) : Boolean {
        print("x=$x y= $y")
        return x - xCor >= 0.01 && xCor + button.width - x >= 0.01
                && y - yCor >= 0.01 && yCor + button.height - y >= 0.01
    }

    fun free() {
        button.dispose()
    }


}