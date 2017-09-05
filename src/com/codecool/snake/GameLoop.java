package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.powerups.SimplePowerup;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

public class GameLoop extends AnimationTimer {

    public Pane pane;

    public GameLoop(Pane pane) {
        this.pane = pane;
    }

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (GameEntity gameObject : Globals.gameObjects) {
            if (gameObject instanceof Animatable) {
                Animatable animObject = (Animatable)gameObject;
                animObject.step();
            }
        }
        //TODO add randomity, add new powerups as well
        if(Globals.spaceDown) {
            Globals.addGameObject(new Laser(pane));
            Globals.spaceDown = false;
        }
        //Globals.addGameObject(new SimplePowerup(pane));

        Globals.gameObjects.addAll(Globals.newGameObjects);
        Globals.newGameObjects.clear();

        Globals.gameObjects.removeAll(Globals.oldGameObjects);
        Globals.oldGameObjects.clear();
    }
}
