package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);

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
            spawnEnemies();
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            spawnEnemies();
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        spawnEnemies();
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void spawnEnemies() {
        Random random = new Random();
        int randomNum = random.nextInt(30);

        if (randomNum == 15) {
            new SimplePowerup(this);
//        } else if (randomNum == 25) {
//            new DecreasingSpeedPowerUp(this);
        } else if (randomNum == 5) {
            new IncreasingSpeedEnemy(this);
        }
    }
}
