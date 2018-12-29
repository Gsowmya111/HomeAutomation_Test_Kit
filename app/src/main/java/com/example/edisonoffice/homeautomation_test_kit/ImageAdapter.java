package com.example.edisonoffice.homeautomation_test_kit;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    ImageView imageView;
    Bluetooth bluetooth;
    public Integer[] images={
            R.drawable.switch1, R.drawable.rgb, R.drawable.dimmer,R.drawable.all_on,R.drawable.all_off

    };

    public ImageAdapter(Context c)
    {
        context=c;
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.single_grid_item, null);
         //   TextView textView = (TextView) grid.findViewById(R.id.grid_text);
           imageView = (ImageView)grid.findViewById(R.id.grid_image);
           // textView.setText(images[position]);
            imageView.setImageResource(images[position]);
        } else {
            grid = (View) convertView;
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = null;
                FragmentManager fragmentManager =  ((Activity) context).getFragmentManager();

                switch (position) {
                    case 0:
                        fr = new Fragmentswitch5plus1();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_place, fr).addToBackStack(null).commit();
                        break;

                    case 1:
                        fr = new Rgb_frag();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_place, fr).addToBackStack(null).commit();
                        break;
                    case 2:
                        fr = new Dimmer_main_frag();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_place, fr).addToBackStack(null).commit();
                        break;

                    case 3:
                        if (Bluetooth.BT_Connected) {
                            bluetooth.btOUT("*A1#".getBytes(), true);

                        }

                        break;
                    case 4:
                        if (Bluetooth.BT_Connected) {
                            bluetooth.btOUT("*A0#".getBytes(), true);
                        }
                        break;

                }

            }
        });


        return grid;
    }
}
