package com.example.myapplication;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {
//screen größe wird festgelegt
    static BitmapBank bitmapBank; //Bitmap object reference to store images
    static GameEngine gameEngine; // GameEngine object reference
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;

    public static void initialization(Context context, GameView gameView) {
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        gameEngine = new GameEngine(gameView);
        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = -30;
    }

    //Return BitmapBank instance
    public static BitmapBank getBitmapBank() {
        return bitmapBank;
    }

    //return GameEngine instance
    public static GameEngine getGameEngine() {
        return gameEngine;
    }

    private static void setScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
    }
}