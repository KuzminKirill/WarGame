package com.wardemo.game.states


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.wardemo.game.WarDemo
import com.wardemo.game.core.Coin
import com.wardemo.game.core.Hero
import com.sun.awt.SecurityWarning.setPosition
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.wardemo.game.core.Ground


class LevelState(gsm :GameStateManager) : State(gsm) {

    var hero : Hero
    var coin : Coin
    private val leftBtn: Texture
    private val rightBtn: Texture

    private val back: Texture
    //private val ground: Texture
    var ground1: Ground
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
        back = Texture("Sky.jpg")
        ground1 = Ground()

    }


    override fun handleInput() {


        if (Gdx.input.isTouched) {
            if ((Gdx.input.x > 1600) && (Gdx.input.x < 1700) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                hero.jump()
            }


                if ((Gdx.input.x > 900) && (Gdx.input.x < 1100) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                    hero.move_right()
                }

                if ((Gdx.input.x > 0) && (Gdx.input.x < 150) && (Gdx.input.y > 950) && (Gdx.input.y < 1080)) {
                    hero.move_left()
                }
            }
            else hero.velocity.x = 0f

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

        sb.projectionMatrix = camera.combined
        sb.begin()

        sb.draw(back, 0f, 0f)
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(ground1.getgr(), ground1.getposgrx(), ground1.getposgry())
        sb.draw(hero.hero, hero.position.x,hero.position.y)

        font.draw(sb, "SCORE: " + score, 0f,400f)
        sb.draw(leftBtn, 0f, 0f)
        sb.draw(rightBtn, 25f, 0f)
        sb.draw(hero.hero, hero.position.x, hero.position.y)
        sb.draw(coin.coin, coin.position.x, coin.position.y)


        sb.end()
    }

    override fun dispose() {
        rightBtn.dispose()
        leftBtn.dispose()
        font.dispose()
        hero.free()
    }
}