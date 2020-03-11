package com.example.harjoitus9;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private GestureDetector detector;
    private LinearLayout linearLayout;


    public class MyGesture extends GestureDetector.SimpleOnGestureListener {

        int vari;

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            int musta;
            int valkoinen;
            //kokeile vielä true false boolena arvoilla määrittäen vaikka musta tureksi tms


            if(vari == Color.rgb(0,0,0)){
                linearLayout.setBackgroundColor(Color.rgb(255,255,255));
            }
            else{
                linearLayout.setBackgroundColor(Color.rgb(0,0,0));
            }

            return super.onDoubleTap(e);


        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            //tänne muutetaan väri x- ja y-akselin mukaisesti
            //https://developer.android.com/guide/topics/graphics/fling-animation#java
            linearLayout.setBackgroundColor(Color.rgb(123,124,123));


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



        this.detector= new GestureDetector(this, new MyGesture());




    }
}
