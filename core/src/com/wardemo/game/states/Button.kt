package com.wardemo.game.states


import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3


class Button(ldX : Float, ldY : Float, ruX : Float, ruY :Float, view : String) {

    var leftDownPosition : Vector3
    var rightUpPosition : Vector3
    var button : Texture
    init {
        leftDownPosition = Vector3(ldX,ldY,0f)
        rightUpPosition = Vector3(ruX,ruY,0f)
        button = Texture(view)
    }

    fun isTouched(x : Float, y : Float) : Boolean {
        //Resources
        ///Вот тут должны быть вызовы
        return x - leftDownPosition.x >= 0.001 && rightUpPosition.x - x >= 0.001 &&
                y - leftDownPosition.y >= 0.001 && rightUpPosition.y - y >= 0.001
    }

    fun free() {
        button.dispose()
    }


}