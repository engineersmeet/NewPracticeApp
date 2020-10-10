package com.pravin.lede.gl.myapplication.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.pravin.lede.gl.myapplication.R;

@SuppressLint("AppCompatCustomView")
public class CircularImageView extends ImageView {

    private RectF mBitmapDrawBounds;
    private Paint borderPaint;

    public CircularImageView(Context context) {
        super(context);
    }

    public CircularImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView, 0, 0);
            a.recycle();
        }

        mBitmapDrawBounds = new RectF();
        borderPaint = new Paint();
        borderPaint.setColor(Color.RED);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setAntiAlias(true);
        borderPaint.setStrokeWidth(20.0F);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        invalidate();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        invalidate();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBitmapDrawBounds.set(calculateBounds());
        float radius = Math.min(mBitmapDrawBounds.width() / 2.0F, mBitmapDrawBounds.height() / 2.0F);
        Path path = new Path();
        path.addCircle(mBitmapDrawBounds.centerX(), mBitmapDrawBounds.centerY(), radius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
        canvas.drawCircle(mBitmapDrawBounds.centerX(), mBitmapDrawBounds.centerY(), radius, borderPaint);
    }

    private RectF calculateBounds() {
        // w = 100, h = 150
        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        // SL = 100
        int sideLength = Math.min(availableWidth, availableHeight);

        // L = 0
        float left = getPaddingLeft() + (availableWidth - sideLength) / 2f;
        // T = 150 - 100 = 50/2 = 25
        float top = getPaddingTop() + (availableHeight - sideLength) / 2f;

        //0, 25, 100, 125
        return new RectF(left, top, left + sideLength, top + sideLength);
    }

}
