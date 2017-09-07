package com.codecool.snake;

import com.codecool.snake.entities.enemies.FasterEnemy;
import com.codecool.snake.entities.enemies.GhostEnemy;
import com.codecool.snake.entities.enemies.NotSoSimpleEnemy;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.laser.Laser;
import com.codecool.snake.entities.powerups.FillHealthPowerup;
import com.codecool.snake.entities.powerups.GoFasterPowerup;
import com.codecool.snake.entities.powerups.MakeSnakeLongerPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;

public class Game extends Pane {

    SnakeHead snakeHead = new SnakeHead(this, 500, 500);

    public Game() {

        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);
        new NotSoSimpleEnemy(this, snakeHead);

        new GhostEnemy(this);
        new GhostEnemy(this);  

        new FasterEnemy(this);
        new FasterEnemy(this);

        new SimpleEnemy(this);
        new SimpleEnemy(this);

        new SimplePowerup(this);
        new SimplePowerup(this);

        new MakeSnakeLongerPowerUp(this);
        new MakeSnakeLongerPowerUp(this);

        new FillHealthPowerup(this);
        new FillHealthPowerup(this);
        new GoFasterPowerup(this);
        new GoFasterPowerup(this);
        new GoFasterPowerup(this);
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
                case R:
                    restart();
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

    public void restart(){
        Globals.gameLoop.stop();
        for(GameEntity entity: Globals.gameObjects) {
            if (entity instanceof SnakeBody || entity instanceof SnakeHead || entity instanceof Laser){
                entity.destroy();
            }
            Random rnd = new Random();
            entity.setCoordinates((int) (rnd.nextDouble() * Globals.WINDOW_WIDTH),
                                  (int) (rnd.nextDouble() * Globals.WINDOW_HEIGHT));
        }
        snakeHead = new SnakeHead(this, 500, 500);
        Globals.score = 0;
        Globals.gameLoop.start();
    }
}
