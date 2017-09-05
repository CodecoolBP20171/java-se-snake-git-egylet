package com.codecool.snake;

import com.codecool.snake.entities.enemies.FasterEnemy;
import com.codecool.snake.entities.enemies.GhostEnemy;
import com.codecool.snake.entities.enemies.NotSoSimpleEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        SnakeHead snakeHead = new SnakeHead(this, 500, 500);

        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);

        /*new GhostEnemy(this);
        new GhostEnemy(this);
        new GhostEnemy(this);
        new GhostEnemy(this);

        new FasterEnemy(this);
        new FasterEnemy(this);
        new FasterEnemy(this);
        new FasterEnemy(this);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);*/
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}
