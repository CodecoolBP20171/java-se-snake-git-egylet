package com.codecool.snake.entities.laser;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.*;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


public class Laser extends GameEntity implements Animatable, Interactable {

    private Point2D heading;

    public Laser(Pane pane, SnakeHead snakeHead) {
            super(pane);
            int speed = 10;
            setImage(Globals.laser);
            pane.getChildren().add(this);
            double direction = snakeHead.getDir();
            setRotate(direction);
            heading = Utils.directionToVector(direction, speed);
            double xc = snakeHead.getX() - 34;
            double yc = snakeHead.getY() - 115;
            setX(xc);
            setY(yc);
    }

    @Override
    public void apply(SnakeHead snakeHead) {}

    @Override
    public void apply(Laser laser) {}

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof SimpleEnemy || entity instanceof GhostEnemy || entity instanceof FasterEnemy || entity instanceof NotSoSimpleEnemy) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    destroy();
                }
            }
        }
    }

    @Override
    public double getDir() {
        return 0;
    }
}
