package com.example.edisonoffice.homeautomation_test_kit;

/**
 * Created by Deepak Rao J on 5/25/2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomList extends ArrayAdapter<String> {
static List<ImageView> img=new ArrayList<ImageView>();
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.item_qq, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView= inflater.inflate(R.layout.item_qq, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt_item_qq_title);

        final ImageView imageView = (ImageView) rowView.findViewById(R.id.img_item_qq);
            img.add(imageView);

        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}