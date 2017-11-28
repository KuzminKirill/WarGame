package com.wardemo.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wardemo.game.WarDemo
import com.wardemo.game.core.Hero
import com.sun.awt.SecurityWarning.setPosition
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton


class LevelState(gsm :GameStateManager) : State(gsm) {

    var hero : Hero
    private val leftBtn: Texture
    private val rightBtn: Texture
    private val back: Texture

    init {
        hero = Hero(50f,50f)
        camera.setToOrtho(false,WarDemo.WIDTH / 2f, WarDemo.HEIGHT / 2f)
        leftBtn = Texture("left.png")
        rightBtn = Texture("right.png")
        back = Texture("Sky.jpg")

    }
    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            hero.jump()
            //hero.move_right()
        }
        if((Gdx.input.x < 100) && (Gdx.input.y < 100))   {
            hero.move_right()
        }

        if((Gdx.input.x > 100) && (Gdx.input.y > 100))   {
            hero.move_left()
        }
    }

    override fun update(dt: Float) {
        handleInput()
        hero.update(dt)
    }

    //fun leftbtn() {
    //    if((Gdx.input.x < 100) && (Gdx.input.y < 100))   {
    //        hero.move_right()
    //    }
    //}

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = camera.combined
        sb.begin()
        sb.draw(back, 0f, 0f)
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(rightBtn, 25f, 0f)
        sb.draw(hero.hero, hero.position.x,hero.position.y)

        sb.end()
    }

    override fun dispose() {

    }
}