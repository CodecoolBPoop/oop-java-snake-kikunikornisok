package com.codecool.snake;

import com.codecool.snake.entities.enemies.IncreasingSpeedEnemy;
import com.codecool.snake.entities.enemies.ChangeDirection;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.DecreasingSpeedPowerup;
import com.codecool.snake.entities.powerups.ShieldPowerUP;
import com.codecool.snake.entities.powerups.FoodPowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Random;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);

        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new SimpleEnemy(this);
        new ChangeDirection(this);
        new ChangeDirection(this);

        new FoodPowerup(this);
        new FoodPowerup(this);
        new FoodPowerup(this);
        new FoodPowerup(this);

        new ShieldPowerUP(this);

        new IncreasingSpeedEnemy(this);
        new IncreasingSpeedEnemy(this);
        new IncreasingSpeedEnemy(this);

        new DecreasingSpeedPowerup(this);
        new DecreasingSpeedPowerup(this);
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

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public void spawnEntities() {
        Random random = new Random();
        int randomNum = random.nextInt(300);

        if (randomNum == 150) {
            new FoodPowerup(this);
        } else if (randomNum == 50) {
            new IncreasingSpeedEnemy(this);
        }

        if (Globals.gameTimeAtStart % 300 == 0) {
            new DecreasingSpeedPowerup(this);
            new ChangeDirection(this);
        }
    }
}
