package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Main;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;


public class FasterEnemy extends GameEntity implements Animatable, Interactable {

    private static final int damage = 10;

    public FasterEnemy(Pane pane, SnakeHead player) {
        super(pane);
        setImage(Globals.fastEnemy);
        pane.getChildren().add(this);
        setSpeed();
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
  
    public void setSpeed(){
        this.speed = 2;
    }
}
