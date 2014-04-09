package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mikennaeverett.momentummaze.app.GestureFilter.SimpleGestureListener;
import android.view.MotionEvent;


/**
 * Created by mikenna on 4/7/14.
 */
public class MazeLevelActivity extends Activity implements View.OnClickListener, SimpleGestureListener {
    private GestureFilter detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        Button button = (Button)this.findViewById(R.id.resetButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.previousLevelButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.nextLevelButton);
        button.setOnClickListener(this);

        // Detect touched area
        detector = new GestureFilter(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maze, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.previousLevelButton) {
            Toast toast = Toast.makeText(this, "This would take you to the previous level!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (view.getId() == R.id.resetButton) {
            Toast toast = Toast.makeText(this, "This is where a reset would happen!", Toast.LENGTH_SHORT);
            toast.show();
        }
        if (view.getId() == R.id.nextLevelButton) {
            Toast toast = Toast.makeText(this, "This would take you to the next level!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }
    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case GestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                break;
            case GestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                break;
            case GestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                break;
            case GestureFilter.SWIPE_UP :    str = "Swipe Up";
                break;

        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }
}
