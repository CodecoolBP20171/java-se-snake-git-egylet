package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.laser.Laser;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeBody extends GameEntity implements Animatable, Interactable {

    private GameEntity parent;
    private Queue<Vec2d> history = new LinkedList<>();
    private static final int historySize = 10;
    private SnakeHead snakeHead;

    public SnakeBody(Pane pane, GameEntity parent, SnakeHead snakeHead) {
        super(pane);
        this.parent = parent;
        this.snakeHead = snakeHead;
        setImage(Globals.snakeBody);

        // place it visually below the current tail
        List<Node> children = pane.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        setX(xc);
        setY(yc);
        for (int i = 0; i < historySize; i++) {
            history.add(new Vec2d(xc, yc));
        }
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(-100);
    }

    @Override
    public void apply(Laser laser) {}

    public void step() {
        Vec2d pos = history.poll(); // remove the oldest item from the history
        setX(pos.x);
        setY(pos.y);
        history.add(new Vec2d(parent.getX(), parent.getY())); // add the parent's current position to the beginning of the history
    }

    @Override
    public double getDir(){
        return 0;
    }
}
