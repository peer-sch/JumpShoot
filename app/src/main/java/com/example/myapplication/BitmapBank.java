package com.example.myapplication;

import static android.opengl.ETC1.getWidth;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class BitmapBank {
//Bilder werden in der Bitmap gespeichert und die Bitmap Bilder werde  Scaliert
    Bitmap background;
    Bitmap[] bird;
    Bitmap[] block;
    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background_main);
        background = scaleImage(background);
        bird = new Bitmap[4];
        bird[0] = BitmapFactory.decodeResource(res, R.drawable.mensch1);
        bird[1] = BitmapFactory.decodeResource(res, R.drawable.mensch2);
        bird[2] = BitmapFactory.decodeResource(res, R.drawable.mensch3);
        bird[3] = BitmapFactory.decodeResource(res, R.drawable.mensch4);

        block = new Bitmap[4];
        block[0] = BitmapFactory.decodeResource(res, R.drawable.mensch1);
        block[1] = BitmapFactory.decodeResource(res, R.drawable.mensch2);
        block[2] = BitmapFactory.decodeResource(res, R.drawable.mensch3);
        block[3] = BitmapFactory.decodeResource(res, R.drawable.mensch4);
    }

    //Return bird bitmap pg frame
    public Bitmap getBird(int frame){

        return bird[frame];
    }

    public int getBirdWidth(){

        return bird[0].getWidth();
    }

    public int getBirdHeight(){

        return bird[0].getHeight();
    }

    //Return block bitmap pg frame
    public Bitmap getBlock(int frame){

        return block[frame];
    }

    public int getBlockWidth(){

        return block[0].getWidth();
    }

    public int getBlockHeight(){

        return block[0].getHeight();
    }
    //Return background bitmap
    public Bitmap getBackground(){

        return background;
    }

    //Return background width
    public int getBackgroundWidth(){

        return background.getWidth();
    }

    //Retrun background height
    public int getBackgroundHeight(){

        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        if (bitmap == null || bitmap.getHeight() == 0) {
            return null;
        }
        float widthHeightRatio = (float) bitmap.getWidth() / bitmap.getHeight();
        //We will multiply widhtHeightRatio with screenHeight to get scaled width of the bitmap
        //Then call createScaleBitmap() to create a new bitmap, scaled from an existing bitmap, when possible
        int backgroundScaledWidth = (int) (widthHeightRatio * AppConstants.SCREEN_HEIGHT);
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
