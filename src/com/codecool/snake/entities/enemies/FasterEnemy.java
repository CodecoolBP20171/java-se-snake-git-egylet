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


public class FasterEnemy extends GameEntity implements Animatable, Interactable {
    private Point2D heading;
    private static final int damage = 10;

    public FasterEnemy(Pane pane, SnakeHead player) {
        super(pane);

        setImage(Globals.fastEnemy);
        pane.getChildren().add(this);
        setEnemy(player);
        this.setNewHeading();
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
        destroy();
    }

    @Override
    public void apply(Laser laser) {
        destroy();
    }

    @Override
    public double getDir() {
        return 0;
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

    public Point2D setNewHeading(){
        int speed = 2;
        double direction = rnd.nextDouble() * 360;
        System.out.println("Simple: " + direction);
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
        System.out.println(heading);
        return heading;
    }
}
