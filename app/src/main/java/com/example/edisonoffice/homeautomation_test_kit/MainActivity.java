package com.example.edisonoffice.homeautomation_test_kit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView list;
    private Context context;
    Fragment fr = null;
    Bluetooth bluetooth;
    public static volatile int active = 0;

    Button btnmood1, btnmood2, btnmood3;
    String[] web = {
            "BEDROOM",
            "CONFERENCE",
            "RECEPTION",
            "DINNING ROOM",
            "ENTRANCE",
            "LIVING ROOM",
            "KITCHEN",
            "PLAY ROOM",
            "STUDY ROOM",

    };
    Integer[] imageId = {
            R.drawable.tbedroom01,
            R.drawable.tconference01,
            R.drawable.treception01,
            R.drawable.tdiningroom01,
            R.drawable.tentrance01,
            R.drawable.tlivingroom01,
            R.drawable.tkitchen01,
            R.drawable.tplayroom01,
            R.drawable.tstudyroom01,

    };
    ImageView btstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btstatus = (ImageView) findViewById(R.id.bt_status);

        Fragment fr = null;
        FragmentManager fragmentManager = getFragmentManager();

        fr = new Fragmentswitch5plus1();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_place, fr).addToBackStack(null).commit();

        if (Bluetooth.BT_Connected) {
            btstatus.setImageResource(R.drawable.bt_on);

        } else {
            btstatus.setImageResource(R.drawable.bt_off);
        }



        btstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,BTCls.class);
                startActivity(i);

            }
        });




        CustomList adapter = new
                CustomList(MainActivity.this, web, imageId);
        list = (ListView) findViewById(R.id.lv_edit);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();

                switch (position) {


                }

            }
        });
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try
        {
            if(Bluetooth.BT_Connected)
            {
                Log.d("msg","bt disconnected in tabhost");
                //Bluetooth.tcpConnectionClose();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Bluetooth.BT_Connected) {
            btstatus.setImageResource(R.drawable.bt_on);

        } else {
            btstatus.setImageResource(R.drawable.bt_off);
        }

    }
}
