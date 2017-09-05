package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class GhostEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private Random rnd = new Random();

    public GhostEnemy(Pane pane) {
        super(pane);

        setImage(Globals.ghostEnemy);
        pane.getChildren().add(this);
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
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
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

    public Point2D setNewHeading(){
        int speed = 1;
        double direction = rnd.nextDouble() * 360;
        System.out.println("Simple: " + direction);
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
        System.out.println(heading);
        return heading;
    }
}
