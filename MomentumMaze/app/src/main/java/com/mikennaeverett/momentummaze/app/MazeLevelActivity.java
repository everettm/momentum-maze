package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mikennaeverett.momentummaze.app.GestureFilter.SimpleGestureListener;


/**
 * Created by mikenna on 4/7/14.
 */
public class MazeLevelActivity extends Activity implements View.OnClickListener, SimpleGestureListener {
    private GestureFilter detector;
    private int levelNumber;
    private int highUnlocked;
    private SharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent(); // gets the previously created intent
        levelNumber = intent.getIntExtra("levelNumber", 0);
        String levNum = Integer.toString(levelNumber);
        Toast.makeText(this, levNum, Toast.LENGTH_SHORT).show();

        Bitmap bg = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bg);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        AssetManager assetManager = getAssets();
//
//        AssetFileDescriptor descriptor = null;
//        try {
//            descriptor = assetManager.openFd("mazeFiles/mazeL1.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FileReader reader = new FileReader(descriptor.getFileDescriptor());
        MazeFragment fragment = new MazeFragment(this, new Maze());
//        MazeFragment fragment = null;
//        try {
//            fragment = new MazeFragment(this, new Maze(reader));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        fragmentTransaction.add(R.id.game_fragment, fragment);
        fragmentTransaction.commit();

        setContentView(R.layout.level);

        prefs = new SharedPrefs(this);
        prefs.loadPrefs();
        highUnlocked = prefs.getHighUnlocked();

        Button button = (Button) this.findViewById(R.id.resetButton);
        button.setOnClickListener(this);
        button = (Button) this.findViewById(R.id.previousLevelButton);
        button.setOnClickListener(this);
        button = (Button) this.findViewById(R.id.nextLevelButton);
        button.setOnClickListener(this);

        button = (Button) this.findViewById(R.id.winLevelButton);
        button.setOnClickListener(this);

        // Detect touched area
        detector = new GestureFilter(this, this);
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
