package com.example.mange.space_shooter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.mange.space_shooter.Characters.Character;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 5/6/18.
 */

public class Space extends View {


    public Space(Context context) {
        super(context);
        Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.playership);
        final int dstWidth = 100;
        final int dstHeight = 100;
        MainActivity.ship = Bitmap.createScaledBitmap(ballSrc, dstWidth, dstHeight, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(MainActivity.ship, MainActivity.xPos, MainActivity.yPos, null);
        invalidate();
        }
    }