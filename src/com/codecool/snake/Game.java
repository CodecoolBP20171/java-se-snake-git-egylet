package com.codecool.snake;

import com.codecool.snake.entities.enemies.FasterEnemy;
import com.codecool.snake.entities.enemies.GhostEnemy;
import com.codecool.snake.entities.enemies.NotSoSimpleEnemy;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Timer;

public class Game extends Pane {
    GameEntity snakeHead;
    public Game() {
        snakeHead = new SnakeHead(this, 500, 500);


        //new NotSoSimpleEnemy(this, snakeHead);

        new GhostEnemy(this);
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
