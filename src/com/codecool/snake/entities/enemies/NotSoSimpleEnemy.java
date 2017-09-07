package com.codecool.snake.entities.enemies;

import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class NotSoSimpleEnemy extends GameEntity implements Animatable, Interactable {

    private static final int damage = 10;
    private SnakeHead snakeHead;

    public NotSoSimpleEnemy(Pane pane, SnakeHead snakeHead) {
        super(pane);
        this.snakeHead = snakeHead;
        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);
        setEnemy(snakeHead);
        setCoordinate();
    }

    @Override
    public void step() {
        followTheSnakeHead();
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        setCoordinate();
    }

    @Override
    public void apply(Laser laser) {
        Globals.score += 20;
        Main.scoreHUD.setText("Score: " + Globals.score);
        setCoordinate();
    }

    @Override
    public double getDir() {
        return 0;
    }

    public void followTheSnakeHead(){
        double speed = 0.008;
        double deltaX = snakeHead.getSnakeHeadX() - getX();
        double deltaY = snakeHead.getSnakeHeadY() - getY();
        double distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        if (distance <= 200) {
            setX(getX() + (deltaX * speed));
            setY(getY() + (deltaY * speed));
        }
    }
}

