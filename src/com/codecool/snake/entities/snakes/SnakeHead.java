package com.codecool.snake.entities.snakes;

import com.codecool.snake.PopUpWindow;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SnakeHead extends GameEntity implements Animatable {

    private float originalSpeed = 2;
    private float actualSpeed = originalSpeed;
    private boolean changeDiversion = false;
    private static final float turnRate = 2;
    private int snakeMainBodyLength = 10;
    private boolean shieldActive = false;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private float startMushroomTime;
    private int snakeNum;

    public SnakeHead(Pane pane, int xc, int yc, int snakeNum) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        this.snakeNum = snakeNum;

        if (snakeNum == 1) {
            setImage(Globals.snakeHeadImages[0]);
        } else if (snakeNum == 2) {
            setImage(Globals.snakeHeadImages[1]);
        }
        pane.getChildren().add(this);

        addPart(snakeMainBodyLength);
    }

    public void step() {
        double dir = getRotate();
        changeDiversion(this.snakeNum, dir, changeDiversion);
        if(Globals.gameTimeAtStart-Globals.shieldActivated == 700) {
            diActivateShield();
        }

        // check if collided with an enemy or a powerup
        List<GameEntity> gameObjectCopy = new ArrayList<>(Globals.gameObjects);
        int snakeBodyLengthToCheck = Globals.twoPlayers ? snakeMainBodyLength * 2 : snakeMainBodyLength;
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
                // check if snake hit it's own tail
                else if (entity instanceof SnakeBody && gameObjectCopy.indexOf(entity) > snakeBodyLengthToCheck + 1) {
                    if(!isShieldActive()){
                        Globals.gameLoop.stop();
                        Globals.popUpStage = new Stage();
                        PopUpWindow popUpWindow = new PopUpWindow();
                        popUpWindow.start(Globals.popUpStage);
                    }
                    System.out.println("You hit your tale! Game Over");
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            System.out.println(Globals.score);
            Globals.gameLoop.stop();
            Globals.popUpStage = new Stage();
            PopUpWindow popUpWindow = new PopUpWindow();
            popUpWindow.start(Globals.popUpStage);
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            tail = new SnakeBody(pane, tail, this.snakeNum);
        }
    }

    public void activateShield(){
        shieldActive = true;
        Globals.shieldActivated = Globals.gameTimeAtStart;
    }

    public void diActivateShield(){
        System.out.println("Shield wears off");
        Globals.shieldActivated = 0;
        shieldActive = false;
    }

    public void changeDiversion(int snakeNum, double dir, boolean change) {
        if (snakeNum == 1) {
            if (change) {
                dir = setDiversion(Globals.leftKeyDown, Globals.rightKeyDown, dir);
            } else {
                dir = setDiversion(Globals.rightKeyDown, Globals.leftKeyDown, dir);
            }
        } else if (snakeNum == 2){
            if (change) {
                dir = setDiversion(Globals.aKeyDown, Globals.dKeyDown, dir);
            } else {
                dir = setDiversion(Globals.dKeyDown, Globals.aKeyDown, dir);
            }
        }
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, actualSpeed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    public double setDiversion(boolean leftKey, boolean rightKey, double dir) {
        if (leftKey) {
            dir = dir + turnRate;
        }
        if (rightKey) {
            dir = dir - turnRate;
        }
        return dir;
    }

    public boolean isShieldActive() { return shieldActive; }

    public float getActualSpeed() {
        return this.actualSpeed;
    }

    public void setActualSpeed(float newSpeed) {
        this.actualSpeed = newSpeed;
    }

    public float getOriginalSpeed() {
        return this.originalSpeed;
    }

    public float getStartMushroomTime() {
        return startMushroomTime;
    }

    public void setStartMushroomTime(float startMushroomTime) {
        this.startMushroomTime = startMushroomTime;
    }

    public void setChangeDiversion(boolean changeDiversion) {
        this.changeDiversion = changeDiversion;
    }

}
