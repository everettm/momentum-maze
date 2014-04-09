package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mikennaeverett.momentummaze.app.SharedPrefs;



/**
 * Created by mikenna on 4/7/14.
 */
public class LevelSelectActivity extends Activity implements View.OnClickListener {
    private int highUnlocked;
    private SharedPrefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_select);

        prefs = new SharedPrefs(this);
        prefs.loadPrefs();
        highUnlocked = prefs.getHighUnlocked();
        //prefs.setHighUnlocked(2);
        //prefs.savePrefs();
        //highUnlocked = prefs.getHighUnlocked();

        Button button = (Button)this.findViewById(R.id.levelOneButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.levelTwoButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.levelThreeButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.levelFourButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.levelFiveButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.levelSixButton);
        button.setOnClickListener(this);
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
        if (view.getId() == R.id.levelOneButton) {
            Intent intent = new Intent(this, MazeLevelActivity.class);
            intent.putExtra("levelNumber",1);
            startActivity(intent);
        }
        else if (view.getId() == R.id.levelTwoButton) {
            if (2 <= highUnlocked){
                Intent intent = new Intent(this, MazeLevelActivity.class);
                intent.putExtra("levelNumber",2);
                startActivity(intent);
            }
            else {
                Toast toast = Toast.makeText(this, "That level is locked", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else if (view.getId() == R.id.levelThreeButton) {
            if (3 <= highUnlocked){
                Intent intent = new Intent(this, MazeLevelActivity.class);
                intent.putExtra("levelNumber",3);
                startActivity(intent);
            }
            else {
                Toast toast = Toast.makeText(this, "That level is locked", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else if (view.getId() == R.id.levelFourButton) {
            if (4 <= highUnlocked){
                Intent intent = new Intent(this, MazeLevelActivity.class);
                intent.putExtra("levelNumber",4);
                startActivity(intent);
            }
            else {
                Toast toast = Toast.makeText(this, "That level is locked", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else if (view.getId() == R.id.levelFiveButton) {
            if (5 <= highUnlocked){
                Intent intent = new Intent(this, MazeLevelActivity.class);
                intent.putExtra("levelNumber",5);
                startActivity(intent);
            }
            else {
                Toast toast = Toast.makeText(this, "That level is locked", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else if (view.getId() == R.id.levelSixButton) {
            if (6 <= highUnlocked){
                Intent intent = new Intent(this, MazeLevelActivity.class);
                intent.putExtra("levelNumber",6);
                startActivity(intent);
            }
            else {
                Toast toast = Toast.makeText(this, "That level is locked", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else {
            Toast toast = Toast.makeText(this, "BAAAH!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
