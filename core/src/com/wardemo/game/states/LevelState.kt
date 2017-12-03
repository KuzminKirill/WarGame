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
import com.wardemo.game.core.Ground


class LevelState(gsm :GameStateManager) : State(gsm) {

    var hero : Hero
    private val leftBtn: Texture
    private val rightBtn: Texture
    private val back: Texture
    //private val ground: Texture
    var ground1: Ground

    init {
        hero = Hero(50f,50f)
        camera.setToOrtho(false,WarDemo.WIDTH / 2f, WarDemo.HEIGHT / 2f)
        leftBtn = Texture("left.png")
        rightBtn = Texture("right.png")
        back = Texture("Sky.jpg")
        ground1 = Ground()

    }


    override fun handleInput() {


        if (Gdx.input.isTouched(1) || Gdx.input.isTouched(0)) {
            if ((Gdx.input.x > 900) && (Gdx.input.x < 1100) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                hero.move_right()
            }

            if ((Gdx.input.x > 0) && (Gdx.input.x < 150) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                hero.move_left()
            }
        }
        else hero.velocity.x = 0f

        if (Gdx.input.isTouched(0)) {
            if ((Gdx.input.x > 1600) && (Gdx.input.x < 1700) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                hero.jump()
            }
        }
        else hero.velocity.y = 0f
    }

    override fun update(dt: Float) {
        handleInput()
        hero.update(dt)
    }




    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = camera.combined
        sb.begin()
        sb.draw(back, 0f, 0f)
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(ground1.getgr(), ground1.getposgrx(), ground1.getposgry())
        sb.draw(hero.hero, hero.position.x,hero.position.y)

        sb.end()
    }

    override fun dispose() {

    }
}