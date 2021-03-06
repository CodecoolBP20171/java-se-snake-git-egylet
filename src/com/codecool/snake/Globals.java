package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image snakeHead = new Image("snake_head.png");
    public static Image snakeBody = new Image("snake_body.png");
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image powerupBerry = new Image("powerup_berry.png");
    public static Image ghostEnemy = new Image("ghost_enemy.png");
    public static Image fastEnemy = new Image("fast_enemy.png");
    public static Image laser = new Image("laserbeam.png");
    public static Image powerupStar = new Image("makeitlonger_powerup.png");
    public static Image healthPowerup = new Image("health_powerup.png");
    public static Image speedPowerup = new Image("go_faster.png");

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean spaceDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static int score = 0;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {

        oldGameObjects.add(toRemove);

    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }

}
