package com.codecool.snake.entities.laser;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Laser extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private GameEntity snakehead;

    public Laser(Pane pane, GameEntity snakehead) {
        super(pane);
        this.snakehead = snakehead;
        int speed = 20;
        setImage(Globals.laser);
        //pane.getChildren().add(this);
        pane.getChildren().add(pane.getChildren().indexOf(snakehead), this);

        //TODO

        double direction = snakehead.getDir();
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);

        //Random rnd = new Random();
//        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
//        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        double xc = snakehead.getX() - 34;
        double yc = snakehead.getY() - 115;
        setX(xc);
        setY(yc);
        System.out.println("Laser " +  xc + " " + yc);


    }

    @Override
    public void apply(SnakeHead snakeHead) {}

    @Override
    public String getMessage() {
        return "Interacted with laser.";
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public double getDir() {
        return 0;
    }
}