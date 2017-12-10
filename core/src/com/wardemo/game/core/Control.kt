package com.wardemo.game.core

import com.wardemo.game.states.customButton

class Control {

    var x : Int = 0
    var y : Int = 0
    var z : Int = 0
    var leftBtn : customButton = customButton(0f,0f, "LeftBtn.png")
    var rightBtn : customButton = customButton(250f,0f,"RightBtn.png")
    var jumpBtn : customButton = customButton(1700f, 0f, "JumpBtn.png")
}