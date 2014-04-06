package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

// I love toast!


import java.util.ArrayList;

public class MazeActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        ArrayList<Button> levelList = new ArrayList<Button>();
        levelList.add((Button)this.findViewById(R.id.levelOneButton));
        levelList.add((Button)this.findViewById(R.id.levelTwoButton));
        levelList.add((Button)this.findViewById(R.id.levelThreeButton));
        levelList.add((Button)this.findViewById(R.id.levelFourButton));
        levelList.add((Button)this.findViewById(R.id.levelFiveButton));
        levelList.add((Button)this.findViewById(R.id.levelSixButton));

        LevelListAdapter levelListAdapter = new LevelListAdapter(this, R.layout.level_list_cell, levelList);
        ListView listView = (ListView)this.findViewById(android.R.id.list);
        listView.setAdapter(levelListAdapter);
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
        
    }
}
