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

public class GhostEnemy extends GameEntity implements Animatable, Interactable {

    private static final int damage = 10;

    public GhostEnemy(Pane pane) {
        super(pane);

        setImage(Globals.ghostEnemy);
        pane.getChildren().add(this);
        setCoordinate();
        setNewHeading();

    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            setX(getX()-getX());
            setY(getY()-getY());
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
    public void apply(Laser lase) {
      Globals.score += 15;
      setCoordinate();
    }

    @Override
    public double getDir() {
        return 0;
    }

    @Override
    public String getMessage() {
        return "15 damage";
    }

}
