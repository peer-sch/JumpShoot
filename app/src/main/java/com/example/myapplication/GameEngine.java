package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    Block block;
    static int gameState;
    static GameView gameView;
    Activity activity;
    static int score = 0;
    private Spielen spielenActivity;

    public GameEngine(GameView gameView) {
        // Initialisierung ohne Activity
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        block = new Block();
        // 0 = Not started
        // 1 = Playing Wird in GameView auf 1 gesetzt
        // 2 = GameOver
        gameState = 0;
        GameEngine.gameView = gameView;
        score = 0;
    }

    public void setSpielenActivity(Spielen spielenActivity) {
        this.spielenActivity = spielenActivity;
    }

    public static void updateScore(int points) {
        score += points;
    }

/*    public static void setTouchEnabled(boolean enabled) {
        if (gameView != null) {
            gameView.setTouchEnabled(enabled);
        }
    } */

    public void updateAndDrawBackgroundImage(Canvas canvas) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() +
                    AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }

    public void updateAndDrawBird(Canvas canvas) {
        if (gameState == 1) {
            bird.update();
        } else {

            bird.setJumping(false);
        }

        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getX(), bird.getY(), null);
        currentFrame++;

        //If it exceeds maxframe re-initialize to 0
        if (currentFrame > bird.maxFrame) {
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }

    public void showCollisionPopup() {
        if (activity != null) {
            Intent intent = new Intent(activity, com.example.myapplication.End.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            // Handle den Fall, wenn activity null ist (z.B. zeige eine Fehlermeldung an)
        }
    }

    public void updateAndDrawBlock(Canvas canvas, Context context) {
        if (gameState == 1) {
            block.update();
           if (bird.getX() > block.getX() + block.getWidth()) {
                score++;
               if (spielenActivity != null) {
                   spielenActivity.updateScoreDisplay();
               }else{
                   Log.d("ScoreUpdate", "Activity null");
               }
                Log.d("ScoreUpdate", "Score: " + score);
            }
        }
        if (gameState == 2) {
            showCollisionPopup();
        }
        int currentFrame = block.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBlock(currentFrame), block.getX(), block.getY(), null);
        currentFrame++;
        // If it exceeds maxframe re-initialize to 0
        if (currentFrame > block.maxFrame) {
            currentFrame = 0;
        }
        block.setCurrentFrame(currentFrame);

    }

    public void checkCollisions() {
        if (bird.getX() < block.getX() + block.getWidth() &&
                bird.getX() + AppConstants.getBitmapBank().getBirdWidth() > block.getX() &&
                bird.getY() < block.getY() + block.getHeight() &&
                bird.getY() + AppConstants.getBitmapBank().getBirdHeight() > block.getY()) {
            // Kollision erkannt, führen Sie hier entsprechende Aktionen aus
            gameState = 2; // Setzen Sie den GameState auf Game Over
        }
    }

    public int getScore() {
        return score;
    }
}
