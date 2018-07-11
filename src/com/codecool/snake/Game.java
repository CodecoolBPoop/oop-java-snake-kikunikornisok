package com.codecool.snake;

import com.codecool.snake.entities.enemies.IncreasingSpeedEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.DecreasingSpeedPowerup;
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
        spawnEnemies();
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void spawnEnemies() {
        Random random = new Random();
        int randomNum = random.nextInt(300);

        if (randomNum == 150) {
            new SimplePowerup(this);
        } else if (randomNum == 250) {
            new DecreasingSpeedPowerup(this);
        } else if (randomNum == 50) {
            new IncreasingSpeedEnemy(this);
        }
    }
}
