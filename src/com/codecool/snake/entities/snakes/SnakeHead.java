package com.codecool.snake.entities.snakes;

import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SnakeHead extends GameEntity implements Animatable {

    private float speed;
    private static final float turnRate = 5;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private double dir;
    private List<SnakeBody> bodyParts = new ArrayList<>();
    private int timer = 0;


    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        speed = 2;
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public void step() {
        dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());


        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable && !(entity instanceof SnakeBody)) {
                    System.out.println(this.getX() + "snake " + this.getY());
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                }
            }
        }
        if (timer > 180) {
            for (SnakeBody snakeBodyPart : bodyParts.subList(2, bodyParts.size())) {
                if (this.intersects(snakeBodyPart.getBoundsInParent())) {
                    Interactable interactable = snakeBodyPart;
                    interactable.apply(this);
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            Globals.gameLoop.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setContentText("You died.\nScore: " + Globals.score +"\n Press 'R' to restart the game after OK.");
            alert.setHeaderText(null);
            alert.show();
        }
        if (timer <= 180) {
            timer++;
            System.out.println(timer);
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail, this);
            bodyParts.add(newPart);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
        Main.healthHUD.setText("Health: " + Integer.toString(health));
    }

    public void changeScore(int diff) {
        Globals.score += diff;
    }

    public double getSnakeHeadX(){
        return getX();
    }

    public double getSnakeHeadY() {
        return getY();
    }

    public double getDir() {
        return dir;
    }

    public int getHealth() {
        return health;
    }

    public void setSpeed(float plusSpeed) {
        this.speed += plusSpeed;
    }
}
