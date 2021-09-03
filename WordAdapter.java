package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{


    private int mcolorResourceId;
    public WordAdapter(Activity context, ArrayList<Word> words, int colorId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mcolorResourceId = colorId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

         Word currentword = getItem(position);


        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_letters);
        miwokTextView.setText(currentword.getMiwokTranlation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_letters);
        defaultTextView.setText(currentword.getDefaultTranlation());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);


        if(currentword.hasImage()) {
            iconView.setImageResource(currentword.getImageAddress());
            iconView.setVisibility(View.VISIBLE);
        }
        else{
            iconView.setVisibility(View.GONE);
        }
//        .getImageResourceId()
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mcolorResourceId);

        textContainer.setBackgroundColor(color);
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView


        return listItemView;
    }
}
