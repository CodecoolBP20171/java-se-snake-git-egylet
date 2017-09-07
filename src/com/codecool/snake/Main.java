package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    public static Label healthHUD = new Label();
    public static Label scoreHUD = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        setLabelProperties(game);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT));
        primaryStage.show();
        game.start();
    }

    public void setLabelProperties(Game game){
        game.getChildren().add(healthHUD);
        game.getChildren().add(scoreHUD);
        scoreHUD.setText("Score: 0");
        scoreHUD.setFont(new Font("Arial", 25));
        scoreHUD.setTranslateX(0);
        scoreHUD.setTranslateY(0);
        healthHUD.setText("Health: 100");
        healthHUD.setFont(new Font("Arial", 25));
        healthHUD.setTranslateX(0);
        healthHUD.setTranslateY(50);
        scoreHUD.setStyle("-fx-border-color:lightblue; -fx-background-color:lightblue;");
        healthHUD.setStyle("-fx-border-color:lightblue; -fx-background-color:lightblue;");
    }

}
