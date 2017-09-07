package com.codecool.snake;

import com.codecool.snake.entities.enemies.FasterEnemy;
import com.codecool.snake.entities.enemies.GhostEnemy;
import com.codecool.snake.entities.enemies.NotSoSimpleEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    SnakeHead snakeHead = new SnakeHead(this, 500, 500);

    public Game() {

        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);

        new GhostEnemy(this, snakeHead);
        new GhostEnemy(this, snakeHead);

        new FasterEnemy(this, snakeHead);
        new FasterEnemy(this, snakeHead);

        new SimpleEnemy(this, snakeHead);
        new SimpleEnemy(this, snakeHead);

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case SPACE:
                    if (!Globals.spaceDown) {
                        Globals.addGameObject(new Laser(this, snakeHead));
                        Globals.spaceDown = true;
                    }
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case SPACE: Globals.spaceDown = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }
}
