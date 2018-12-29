package com.example.edisonoffice.homeautomation_test_kit;

/**
 * Created by edison office on 12/15/2018.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;

public class BTCls extends Activity {
    public static volatile int active = 0;
    protected boolean btConnected = false;
    ImageView iv;
    Bluetooth bluetooth;
    private static final int READ_BYTE = 1;
    private static final int READ_LINE = 2;
    TextView tv1;
    public String macId;
    SQLiteDatabase db;
    String id = "";

    EditText et;

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUnameValue = "";
    private String UnameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.bluetooth);

        iv = (ImageView) findViewById(R.id.image);
        Button save = (Button) findViewById(R.id.Save);
        et = (EditText) findViewById(R.id.blutoothtext);
        tv1 = (TextView) findViewById(R.id.textView1);

            loadPreferences();

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                macId = et.getText().toString();

                if (macId.length() == 17) {
                   // Insert_mid(macId);

                    Toast.makeText(getApplicationContext(), "connecting...",
                            Toast.LENGTH_SHORT).show();
                    try {
                        Static_variables.mac_id = macId;
                        Thread.sleep(200);
                        bluetooth = new Bluetooth(hm);
                        if (Bluetooth.BT_Connected) {
                            Toast.makeText(getApplicationContext(), "connected",
                                    Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "not connected",
                                    Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Bt Not Connected",
                                Toast.LENGTH_SHORT).show();
                    }

                } else
                    Toast.makeText(getApplicationContext(), "invalid mac id",
                            Toast.LENGTH_LONG).show();
                // iv.setBackgroundResource(R.drawable.bluetooth3);

            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();

    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        UnameValue = et.getText().toString();
    //    PasswordValue = edt_password.getText();
        System.out.println("onPause save name: " + UnameValue);
        System.out.println("onPause save password: " + PasswordValue);
        editor.putString(PREF_UNAME, UnameValue);
      //  editor.putString(PREF_PASSWORD, PasswordValue);
        editor.commit();
    }

    private void loadPreferences() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        // Get value
        UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
     //   PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
        et.setText(UnameValue);
     //   edt_password.setText(PasswordValue);
        System.out.println("onResume load name: " + UnameValue);
        System.out.println("onResume load password: " + PasswordValue);
    }



    private Handler hm = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case READ_BYTE:
                    byte[] readBuf = (byte[]) msg.obj;
                    final String readMessage = new String(readBuf, 0, msg.arg1);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            tv1.setText(readMessage);
                        }
                    });
                case READ_LINE:
                    final String readMessage2 = (String) msg.obj;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            tv1.setText(readMessage2);
                        }
                    });
            }
        }
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(BTCls.this,MainActivity.class);
        startActivity(i);
        finish();


    }
}
