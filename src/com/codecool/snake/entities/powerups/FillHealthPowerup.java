package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

public class FillHealthPowerup extends GameEntity implements Interactable {

    public FillHealthPowerup(Pane pane) {
        super(pane);
        setImage(Globals.healthPowerup);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getHealth() < 100) {
            snakeHead.changeHealth(10);
            destroy();
        }
    }

    @Override
    public void apply(Laser laser) {
        destroy();
    }

    @Override
    public double getDir(){
        return 0;
    }
}
