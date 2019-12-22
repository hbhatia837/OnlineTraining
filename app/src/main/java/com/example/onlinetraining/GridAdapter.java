package com.example.onlinetraining;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

public class GridAdapter extends ArrayAdapter
{
private  Integer[] gridImages;
private Activity context;
Toolbar toolbar;




    public GridAdapter(Activity context, Integer[] gridImages) {
        super(context,R.layout.grid_layout,gridImages);

        this.context=context;
        this.gridImages=gridImages;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
View v1=inflater.inflate(R.layout.grid_layout,parent,false);
        ImageButton imageView=v1.findViewById(R.id.image1);

imageView.setImageResource(gridImages[position]);
return v1;







    }
}
