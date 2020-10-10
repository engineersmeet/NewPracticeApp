package com.pravin.lede.gl.myapplication.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.pravin.lede.gl.myapplication.R;

import java.util.Random;

public class SnakeGameCustomView extends View implements View.OnClickListener, View.OnTouchListener {

    private Paint paint;

    int length = 90;
    int left = 30;
    int top = 30;
    int right = length + left;
    int bottom = 60;

    int temp = 0;
    int navigation = 0; // left to right, right tp left 2, top to bottom 3, bottom to top 4

    int randomNumber = 100, randomLeft, randomRight, randomTop, randomBottom;

    Handler drawingHandler = null;
    Runnable drawingRunnable = null;

    public SnakeGameCustomView(Context context) {
        super(context);
    }

    public SnakeGameCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.QuestionAnsCustomView, 0, 0);
            int speed = a.getInt(R.styleable.SnakeGameCustomView_snackSpeed, 0);
        }

        setOnClickListener(this);
        setOnTouchListener(this::onTouch);
        paint = new Paint(Color.BLACK);
        paint.setStrokeWidth(3F);
        updateRandom();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < length; i++){
            left +=30;
            right +=30;
            top += 30;
            bottom += 30;
            canvas.drawRect(left, top, right, bottom, paint);
        }
        canvas.drawRect(randomLeft, randomTop, randomRight, randomBottom, paint);
        if (getWidth() - 50 <= right && drawingHandler != null) {
            drawingHandler.removeCallbacks(drawingRunnable);
            drawingHandler = null;
        }
    }

    private int getNavigation() {
        return navigation;
    }

    @Override
    public void onClick(View v) {
        if (drawingHandler == null) {
            drawingHandler = new Handler();
            drawingRunnable = new Runnable() {
                @Override
                public void run() {
                    switch (navigation) {
                        case 1:
                            temp = left;
                            left = left + 10;
                            right = 10 + right;
                            bottom = top + 30;
                            Log.d("Pravin", "Right : "+ right+" | Random Left : "+randomLeft);
                            if(right == randomLeft && (top <= randomTop + 25 || top >= randomTop + 25 || bottom <= randomBottom + 25 || bottom >= randomBottom + 25)){
                                updateRandomPoint();
                            }
                            break;
                        case 2:
                            temp = right;
                            right = right - 10;
                            left =  left - 10;
                            bottom = top + 30;
                            Log.d("Pravin", "left : "+ left+" | Random Right : "+randomRight);
                            if(left == randomRight && (top <= randomTop + 25 || top >= randomTop + 25 || bottom <= randomBottom + 25 || bottom >= randomBottom + 25)){
                                updateRandomPoint();
                            }
                            break;
                        case 3:
                            temp = top;
                            top = top + 10;
                            bottom = 10 + bottom;
                            right = left + 30;
                            Log.d("Pravin", "bottom : "+ bottom+" | Random top : "+randomTop);
                            if(bottom == randomTop && (left <= randomLeft + 25 || left >= randomLeft +25 || right <= randomRight+25 || right >= randomRight +25)){
                                updateRandomPoint();
                            }
                            break;
                        case 4:
                            temp = bottom;
                            bottom = bottom - 10;
                            top = top - 10;
                            right = left + 30;
                            Log.d("Pravin", "top : "+ top+" | Random bottom : "+randomBottom);
                            if(top == randomBottom && (left <= randomLeft + 25 || left >= randomLeft +25 || right <= randomRight+25 || right >= randomRight +25)){
                                updateRandomPoint();
                            }
                            break;
                    }
                    invalidate();
                    drawingHandler.postDelayed(this, 40);
                }


            };
            drawingHandler.post(drawingRunnable);
        }
    }

    private void updateRandomPoint() {
        Random random = new Random();
        randomNumber = random.nextInt(getWidth()-50);
        randomNumber = randomNumber / 10;
        randomNumber = randomNumber * 10;
        updateRandom();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getNavigation() == 0) {
                navigation = 1;
            } else if ((getNavigation() == 1 || getNavigation() == 2) && event.getY() > top) {
                bottom = top + length;
                navigation = 3;
            } else if ((getNavigation() == 3 || getNavigation() == 4) && event.getX() > left) {
                right = length + left;
                navigation = 1;
            } else if ((getNavigation() == 3 || getNavigation() == 4) && event.getX() < left) {
                left = right - length;
                navigation = 2;
            } else if ((getNavigation() == 1 || getNavigation() == 2) && event.getY() < bottom) {
                top = bottom - length;
                navigation = 4;
            }
            Toast.makeText(getContext(), "" + event.getX() + " | " + event.getY() + " | " + getNavigation(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void updateRandom(){
        randomLeft = randomNumber;
        randomRight = randomLeft + 30;
        randomTop = randomNumber;
        randomBottom = randomTop + 30;
        length = length + 30;
    }
}
