package com.mikennaeverett.momentummaze.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mikenna on 4/7/14.
 */
public class MazeLevelActivity extends Activity implements View.OnClickListener {
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
}
