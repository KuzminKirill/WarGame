package com.wardemo.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wardemo.game.WarDemo
import com.wardemo.game.core.Coin
import com.wardemo.game.core.Hero


class LevelState(gsm :GameStateManager) : State(gsm) {

    var hero : Hero
    var coin : Coin
    private val leftBtn: Texture
    private val rightBtn: Texture
    private var font : BitmapFont
    var score : Int

    init {
        hero = Hero(50f,50f)
        coin = Coin( 100f, 200f)
        camera.setToOrtho(false,WarDemo.WIDTH / 2f, WarDemo.HEIGHT / 2f)
        score = 0
        font = BitmapFont()
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
        coin.update(dt)
    }



    override fun render(sb: SpriteBatch) {

        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera.update()


       // sb.projectionMatrix = camera.combined
        sb.begin()
        font.draw(sb, "SCORE: " + score, 0f,400f)
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(rightBtn, 25f, 0f)
        sb.draw(hero.hero, hero.position.x, hero.position.y)
        sb.draw(coin.coin, coin.position.x, coin.position.y)

        sb.end()
    }

    override fun dispose() {

    }
}