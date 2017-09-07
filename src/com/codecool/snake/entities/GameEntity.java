package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.util.Random;

import java.util.Random;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {
    protected Random rnd = new Random();
    protected Pane pane;
    protected Point2D heading;
    protected Random rnd = new Random();

    protected void setEnemy(SnakeHead player) {
        for (GameEntity entity : Globals.getGameObjects()) {
            if (entity instanceof SnakeBody) {
                while (true) {
                    double posX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
                    double posY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
                    // checking player position
                    if (posX != player.getX() && posY != player.getY() || posX != entity.getX() && posY != entity.getY()) {
                        setX(posX);
                        setY(posY);
                        break;
                    }
                }
            }
        }

        while (true) {
            double posX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            double posY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
            // checking player position
            if (posX != player.getX() && posY != player.getY()) {
                setX(posX);
                setY(posY);
                break;
            }
        }

    }


    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }
    public abstract double getDir();

  
    public void setNewHeading() {
        int speed = 1;
        double direction = rnd.nextDouble() * 360;
        System.out.println("Simple: " + direction);
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
        System.out.println(heading);
    }

    public void setCoordinate(){
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

