package com.codecool.snake.entities.enemies;

import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class SimpleEnemy extends GameEntity implements Animatable, Interactable {

    private static final int damage = 10;

    public SimpleEnemy(Pane pane, SnakeHead player) {
        super(pane);
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        setEnemy(player);
        setCoordinate();
        setNewHeading();
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            setNewHeading();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        setCoordinate();
    }


    @Override
    public void apply(Laser laser) {
        Globals.score += 10;
        Main.scoreHUD.setText("Score: " + Globals.score);
        setCoordinate();
    }

    @Override
    public double getDir(){
        return 0;
    }
}
