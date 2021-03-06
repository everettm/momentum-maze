package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MazeActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        SharedPrefs prefs = new SharedPrefs(this);
        prefs.loadPrefs();
        //prefs.setHighUnlocked(1);
        //prefs.savePrefs();

        Button button = (Button)this.findViewById(R.id.instructionsButton);
        button.setOnClickListener(this);
        button = (Button)this.findViewById(R.id.levelSelectButton);
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
        if (view.getId() == R.id.levelSelectButton) {
            Intent intent = new Intent(this, LevelSelectActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.instructionsButton) {
            Toast toast = Toast.makeText(this, "Just play with it man!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}