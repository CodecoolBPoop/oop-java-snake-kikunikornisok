package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public static Image[] snakeHeadImages = {new Image("snake_head1.png"), new Image("snake_head2.png")};
    public static Image[] snakeBodyImages = {new Image("snake_body1.png"), new Image("snake_body2.png")};
    public static Image simpleEnemy = new Image("simple_enemy.png");
    public static Image squirrel = new Image("squirrel.png");
    public static Image diversionEnemy = new Image("mushroom.png");
    public static Image mouse = new Image("mouse.png");
    public static Image snail = new Image("snail.png");
    public static Image shield = new Image("shield.gif");
    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static boolean aKeyDown;
    public static boolean dKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static GameLoop gameLoop;
    public static Game game;
    public static Stage stage;
    public static Stage popUpStage;
    public static Stage startStage;
    public static Scene scene;
    public static long gameTimeAtStart = 0;
    public static long shieldActivated;
    public static boolean twoPlayers;

    public static int score;

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
