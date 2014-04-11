package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikennaeverett.momentummaze.app.GestureFilter.SimpleGestureListener;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by mikenna on 4/7/14.
 */
public class MazeLevelActivity extends Activity implements View.OnClickListener, SimpleGestureListener {
    private GestureFilter detector;
    private int levelNumber;
    private int highUnlocked;
    private SharedPrefs prefs;
    private Set<Integer> recordedSwipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        prefs = new SharedPrefs(this);
        prefs.loadPrefs();
        highUnlocked = prefs.getHighUnlocked();
        recordedSwipes = new HashSet<Integer>();

        Intent intent = getIntent(); // gets the previously created intent
        levelNumber = intent.getIntExtra("levelNumber", 0);
        String levNum = Integer.toString(levelNumber);
        Toast.makeText(this, levNum, Toast.LENGTH_SHORT).show();

        Button button = (Button)this.findViewById(R.id.resetButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.previousLevelButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.nextLevelButton);
        button.setOnClickListener(this);

        button = (Button)this.findViewById(R.id.winLevelButton);
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
            if (levelNumber == 1) {
                Toast toast = Toast.makeText(this, "There is no previous level", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
            Intent intent = new Intent(this, MazeLevelActivity.class);
            intent.putExtra("levelNumber",levelNumber-1);
            startActivity(intent);
            }
        }
        else if (view.getId() == R.id.resetButton) {
            Toast toast = Toast.makeText(this, "This is where a reset would happen!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if (view.getId() == R.id.nextLevelButton) {
            if (levelNumber == 6) {
                {
                    Toast toast = Toast.makeText(this, "There are no more levels", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else if (levelNumber >= highUnlocked) {

                Toast toast = Toast.makeText(this, "The next level is not unlocked", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                Intent intent = new Intent(this, MazeLevelActivity.class);
                intent.putExtra("levelNumber",levelNumber+1);
                startActivity(intent);
            }

        }
        else if (view.getId() == R.id.winLevelButton) {
            Toast toast = Toast.makeText(this, "level up!", Toast.LENGTH_SHORT);
            toast.show();
            onLevelCompleted();
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
        ImageView img= (ImageView) findViewById(R.id.level_image_display);

        switch (direction) {

            case GestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                recordedSwipes.add(GestureFilter.SWIPE_RIGHT);
                img.setImageResource(R.drawable.cat1);
                break;
            case GestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                recordedSwipes.add(GestureFilter.SWIPE_LEFT);
                img.setImageResource(R.drawable.cat2);
                break;
            case GestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                recordedSwipes.add(GestureFilter.SWIPE_DOWN);
                img.setImageResource(android.R.drawable.cat3);
                break;
            case GestureFilter.SWIPE_UP :    str = "Swipe Up";
                img.setImageResource(R.drawable.cat4);
                recordedSwipes.add(GestureFilter.SWIPE_UP);
                break;

        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        if (recordedSwipes.size() == 4) {
            Toast.makeText(this, "Nice! ALL THE THINGS! Next level!", Toast.LENGTH_SHORT).show();
            onLevelCompleted();
        }
    }

    @Override
    public void onDoubleTap() {

    }

    void onLevelCompleted() {
        if (levelNumber == 6) {
            Toast toast = Toast.makeText(this, "You win!", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, MazeActivity.class);
            startActivity(intent);
        }
        if (levelNumber == highUnlocked) {
            prefs.setHighUnlocked(levelNumber+1);
            prefs.savePrefs();
        }
        Intent intent = new Intent(this, MazeLevelActivity.class);
        intent.putExtra("levelNumber",levelNumber+1);
        startActivity(intent);

    }
}
