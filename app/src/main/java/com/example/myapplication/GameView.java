package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;
    SurfaceHolder holder; // Deklariere holder als Instanzvariable
    private static long lastTouchTime = 0;
    private static final long TOUCH_DELAY = 800;
    private boolean isTouchEnabled = true;

    public GameView(Context context) {
        super(context);
        initView();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
       // AppConstants.getGameEngine();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        // Hier wird die gameThread-Initialisierung entfernt
        // if (!gameThread.isRunning()) {
        //     gameThread = new GameThread(holder);
        //     gameThread.start();
        // } else {
        //     gameThread.start();
        // }

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (gameThread != null && gameThread.isRunning()) {
            gameThread.setIsRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // Handle InterruptedException
                }
            }
        }
    }

    void initView() {
        holder = getHolder();  // Ändere holder-Deklaration, um die Instanzvariable zu verwenden
        holder.addCallback(this);
        setFocusable(true);
        // Hier wird die gameThread-Initialisierung verschoben
        gameThread = new GameThread(holder);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("GameView", "onTouchEvent called");
        if (!isTouchEnabled) {
            return true; // Ignoriere das Touch-Ereignis, wenn die Touch-Eingabe deaktiviert ist
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTouchTime < TOUCH_DELAY) {
            return true; // Ignoriere das Touch-Ereignis, wenn die Verzögerung nicht vergangen ist
        }

        int action = event.getAction();
        // Tap is detected
        if (action == MotionEvent.ACTION_DOWN && !AppConstants.getGameEngine().bird.isJumping()) {
            AppConstants.getGameEngine().bird.jump();
            AppConstants.getGameEngine().gameState = 1;
            AppConstants.getGameEngine().bird.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
            lastTouchTime = currentTime; // Aktualisiere die Zeit des letzten Touch-Ereignisses
        }
        return true;
    }
}