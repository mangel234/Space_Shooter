package com.example.mange.space_shooter;

import android.content.Context;
import android.content.res.TypedArray;
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
 * Created by alpha on 4/19/18.
 */

public class Space extends View {
    private final List<SelectionListener> listeners = new ArrayList<>();
    private final Paint boardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public BoardConfiguration board = new BoardConfiguration();

    public Space(Context context, AttributeSet attrs) { //@cons
        this(context, attrs, 0);
    }

    /**
     * Create a new instance by inflating it from XML and apply a class-specific base
     * style from a theme attribute.
     */
    public Space(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSaveEnabled(true);
        //getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
    }

    public interface SelectionListener {

        /**
         * Called when a square of the board is selected by tapping.
         *
         * @param x 0-based column index of the selected square.
         * @param y 0-based row index of the selected square.
         */
        void onSelection(int x, int y);
    }
    @Override
    protected void onDraw(Canvas canvas) {}

    }
