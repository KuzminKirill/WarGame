package com.wardemo.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wardemo.game.WarDemo
import com.wardemo.game.core.Hero


class LevelState(gsm :GameStateManager) : State(gsm) {

    var hero : Hero
    private val leftBtn: Texture
    private val rightBtn: Texture

    init {
        hero = Hero(50f,50f)
        camera.setToOrtho(false,WarDemo.WIDTH / 2f, WarDemo.HEIGHT / 2f)
        leftBtn = Texture("left.png")
        rightBtn = Texture("right.png")
    }
    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            hero.jump()
            hero.move_right()
        }
    }

    override fun update(dt: Float) {
        handleInput()
        hero.update(dt)
    }



    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = camera.combined
        sb.begin()
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(rightBtn, 25f, 0f)
        sb.draw(hero.hero, hero.position.x,hero.position.y)

        sb.end()
    }

    override fun dispose() {

    }
}