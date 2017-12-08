package com.wardemo.game.core

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2



/**
 * Created by n.letov on 08.12.2017.
 */
class Brick(pos : Vector2) {

    var position = Vector2()
        internal set
    var bounds = Rectangle()
        internal set

    init {
        this.position = pos
        this.bounds.width = SIZE
        this.bounds.height = SIZE
    }

    companion object {
        val SIZE = 1f
    }
}