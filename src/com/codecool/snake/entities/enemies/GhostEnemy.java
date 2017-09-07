package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

public class GhostEnemy extends GameEntity implements Animatable, Interactable {

    private static final int damage = 10;

    public GhostEnemy(Pane pane, SnakeHead player) {
        super(pane);

        setImage(Globals.ghostEnemy);
        pane.getChildren().add(this);

        setEnemy(player);

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
    public void apply(Laser laser) {
        Globals.score += 15;
        Main.scoreHUD.setText("Score: " + Globals.score);
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
