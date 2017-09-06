package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

public class NotSoSimpleEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;
    private Random rnd = new Random();
    private SnakeHead snakeHead;

    public NotSoSimpleEnemy(Pane pane, SnakeHead snakeHead) {
        super(pane);
        this.snakeHead = snakeHead;
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

    }

    @Override
    public void step() {
        followTheSnakeHead();
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public void apply(Laser laser) {
        Globals.score += 20;
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

    public void followTheSnakeHead(){
        double speed = 0.008;

        double deltaX = snakeHead.getSnakeHeadX() - getX();
        double deltaY = snakeHead.getSnakeHeadY() - getY();
        double distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        System.out.println(distance);
        if (distance <= 200) {
            setX(getX() + (deltaX * speed));
            setY(getY() + (deltaY * speed));
        }

    }
}

