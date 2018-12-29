package com.example.edisonoffice.homeautomation_test_kit;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
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

public class BT extends Activity {
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

		try {
			id = Reterive_mid();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (id.equals(null))
			et.setText("");
		else {
			et.setText(id);
			Static_variables.mac_id = id;
			try {
				//Static_variables.mac_id = macId;
				Thread.sleep(200);
				bluetooth = new Bluetooth(hm);
				if (Bluetooth.BT_Connected) {
					Toast.makeText(getApplicationContext(), "connected",
							Toast.LENGTH_LONG).show();
					Intent i= new Intent(BT.this,MainActivity.class);
					startActivity(i);
					finish();
					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "not connected",
							Toast.LENGTH_LONG).show();
				}
				// iv.setBackgroundResource(R.drawable.blutooth2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "Bt Not Connected",
						Toast.LENGTH_SHORT).show();
			}
		}
		

		save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				macId = et.getText().toString();

				if (macId.length() == 17) {
					Insert_mid(macId);

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
						// iv.setBackgroundResource(R.drawable.blutooth2);
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

	

	public void Insert_mid(String mid) {
		int bid = 0;
		try {
			Cursor c = null;
			db = openOrCreateDatabase("Automation", MODE_PRIVATE, null);
			c = db.rawQuery("SELECT  MacID from IdTable where id=1;", null);
			c.moveToFirst();
			bid = c.getInt(c.getColumnIndex("MacID"));
			c.close();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bid == 1) {

			try {
				db = openOrCreateDatabase("Automation", MODE_PRIVATE, null);
				// db.execSQL("INSERT INTO BtID VALUES("+mid+");");
				ContentValues cv = new ContentValues();
				cv.put("MacID", mid);
				String whr="id"+"="+1;
				db.update("IdTable", cv, whr, null);
				Log.d("","id updated");
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				db = openOrCreateDatabase("Automation", MODE_PRIVATE, null);
				ContentValues cv = new ContentValues();
				cv.put("MacID", mid);
				cv.put("id", "1");
				db.insert("IdTable", null, cv);
				Log.d("","id inserted");
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String Reterive_mid() {

		String mid = null;
		try {
			Cursor c = null;
			db = openOrCreateDatabase("Automation_Home", MODE_PRIVATE, null);
			c = db.rawQuery("SELECT  MacID from IdTable where id=1;", null);
			c.moveToFirst();
			mid = c.getString(c.getColumnIndex("MacID"));
			c.close();
			db.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return mid;

	}
	@Override
	public void onPause() {
		super.onPause();
		Log.i("onPause", "on pause1");
		
		if(bluetooth.BT_Connected)
		bluetooth.hndlr.removeCallbacksAndMessages(null);
		
		active = 1;
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if (active == 1) {
			
			try{
			id = Reterive_mid();

			if (id.equals(null))
				et.setText("");
			else {
				et.setText(id);
			//	et.setText("20:13:05:24:18:15");

				Static_variables.mac_id = id;
			}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			if (!Bluetooth.BT_Connected
					&& (Static_variables.mac_id).length() == 17) {
				//bluetooth = new Bluetooth(hm);
				
				try {
					//Static_variables.mac_id = macId;
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
					// iv.setBackgroundResource(R.drawable.blutooth2);
				} catch (Exception e) {
					Log.d("in resume", "excep");
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "Bt Not Connected",
							Toast.LENGTH_SHORT).show();
				}
				
			} else if (Bluetooth.BT_Connected) {
				bluetooth = new Bluetooth(hm);
				

			}
			active = 0;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(BT.this,MainActivity.class);
		startActivity(i);
		finish();


	}
}
