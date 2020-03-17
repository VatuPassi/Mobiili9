package com.example.harjoitus9;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GestureDetector detector;
    private LinearLayout linearLayout;

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;


    public class MyGesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            linearLayout = findViewById(R.id.backGround);
            ColorDrawable cd = (ColorDrawable) linearLayout.getBackground();

            if (cd.getColor() == Color.rgb(0, 0, 0)) {
                linearLayout.setBackgroundColor(Color.rgb(255, 255, 255));
            } else {
                linearLayout.setBackgroundColor(Color.rgb(0, 0, 0));
            }

            return super.onDoubleTap(e);
        }

        //fling ei vaihda väriä liukuvasti, vaan pyyhkäisysuunnanmukaisesti
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            //https://developer.android.com/guide/topics/graphics/fling-animation#java
            linearLayout = findViewById(R.id.backGround);
            linearLayout.setBackgroundColor(Color.rgb(123, 124, 123));

            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();

            double vari = Math.abs((double) velocityX);

            // kumpi liike suurempi sivuttainen X (if)  vai pysty Y (else)
            if (Math.abs(diffX) > Math.abs(diffY)) {
                // oikealle tai vasemmalle pyyhkäisy
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        // väri oikealle
                        linearLayout.setBackgroundColor(Color.rgb(255, 0, 0));
                    } else {
                        //väri vasemmalle
                        linearLayout.setBackgroundColor(Color.rgb(0, 255, 0));
                    }
                    return true;
                }

            } else {
                // ylös tai alas pyyhkäisy
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        //väri alas
                        linearLayout.setBackgroundColor(Color.rgb(0, 0, 255));
                    } else {
                        //väri ylös
                        linearLayout.setBackgroundColor(Color.rgb(255, 170, 0));
                    }
                }
                return true;
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {

        detector.onTouchEvent(event);

        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.backGround);

        this.detector = new GestureDetector(this, new MyGesture());
    }
}
