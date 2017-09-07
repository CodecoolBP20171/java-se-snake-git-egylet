package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class FasterEnemy extends GameEntity implements Animatable, Interactable {

    private static final int damage = 10;

    public FasterEnemy(Pane pane) {
        super(pane);

        setImage(Globals.fastEnemy);
        pane.getChildren().add(this);
        setSpeed();
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

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        setCoordinate();
    }

    @Override
    public void apply(Laser laser) {
        setCoordinate();
        Globals.score += 20;
    }

    @Override
    public double getDir() {
        return 0;
    }

    @Override
    public String getMessage() {
        return "20 damage";
    }

    public void setSpeed(){
        this.speed = 2;
    }
}
