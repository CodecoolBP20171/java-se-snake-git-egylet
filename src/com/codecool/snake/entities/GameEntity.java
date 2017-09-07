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

    protected int speed = 1;
    protected Random rnd = new Random();
    protected Pane pane;
    protected Point2D heading;


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
  

    public void setCoordinate(){
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

  
    public Point2D setNewHeading() {
        int direction;
        if (getX() > Globals.WINDOW_WIDTH){
            direction = rnd.nextInt(181) + 180;
        }
        else if (getX() < 0){
            direction = rnd.nextInt(181);
        }
        else if (getY() > Globals.WINDOW_HEIGHT){
            direction = rnd.nextInt(181) + 270;
        }
        else if (getY() < 0){
            direction = rnd.nextInt(181) + 90;
        }
        else{
            direction = rnd.nextInt(361);
        }
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
        return heading;
    }
}

