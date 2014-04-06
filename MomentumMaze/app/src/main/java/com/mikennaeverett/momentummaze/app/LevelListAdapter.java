package com.mikennaeverett.momentummaze.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;



/**
 * Created by mikenna on 4/5/14.
 */
public class LevelListAdapter extends ArrayAdapter<Button> {
    public LevelListAdapter(Context context, int resource, ArrayList<Button> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.level_list_cell, null);
        }

        if (v != null) {
            TextView buttonView = (TextView) v.findViewById(R.id.listCellButtonView);
            buttonView.setText(this.getItem(position).getText());
        }
        return v;
    }
}
