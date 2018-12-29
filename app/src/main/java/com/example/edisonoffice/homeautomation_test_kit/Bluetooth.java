package com.example.edisonoffice.homeautomation_test_kit;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

public class Bluetooth {

	// **************************TCP******************************************
	static Handler hndlr;
	private static final int READ_BYTE = 1;
	private static final int READ_LINE = 2;
	static ReadThread mReadThread = null;
	private static BluetoothAdapter mBluetoothAdapter = null;
	public static BluetoothSocket btSocket = null;
	private static final UUID MY_UUID = UUID
			.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private static BluetoothDevice device = null;

	public static String address = "";// "20:13:05:24:18:15";
	public static InputStream btInputStream;
	private static OutputStream btOutputStream;
	public static BufferedReader btBufferedReader;
	public static boolean BT_ReadLine = false;
	public static volatile boolean BT_Connected = false;
	public static volatile boolean handlerEnable = false;

	Bluetooth(Handler hdl) {
		hndlr = hdl;
		if (btSocket != null) {
			if (!BT_Connected)
				btConnectionOpen();
		} else {
			btConnectionOpen();
		}
	}

	public void btConnectionOpen() {
		try {
			address = Static_variables.mac_id;
			Log.d("", "Address : " + address);
			mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			device = mBluetoothAdapter.getRemoteDevice(address);
			btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
			mBluetoothAdapter.cancelDiscovery();
			btSocket.connect();
			BT_Connected = true;
			BT_ReadLine = true;

			btInputStream = btSocket.getInputStream();
			if (BT_ReadLine) {
				btBufferedReader = new BufferedReader(new InputStreamReader(
						btInputStream));
			}
			btOutputStream = btSocket.getOutputStream();

			if (mReadThread == null) {
				mReadThread = new ReadThread();
				mReadThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void btOUT(byte[] data, boolean flush) {

		try {
			if (BT_Connected) {
				btOutputStream.write(data, 0, data.length);

				if (flush)
					btOutputStream.flush();
				Log.d("msg",new String(data, "UTF-8"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void tcpConnectionClose() {
		try {
			if (BT_ReadLine)
				btBufferedReader.close();
			btInputStream.close();
			btOutputStream.flush();
			btOutputStream.close();
			btSocket.close();
			BT_Connected = false;
			BT_ReadLine = false;
			if (mReadThread != null) {
				mReadThread.interrupt();
				mReadThread = null;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ReadThread extends Thread {

		@Override
		public void run() {
			super.run();
			while (!isInterrupted()) {
				int size;
				try {
					byte[] buffer = new byte[64];
					if (Bluetooth.btInputStream == null) {
						return;
					}
					if (BT_ReadLine) {
						String str = btBufferedReader.readLine();
						Log.d("data from bt stream", "data from stream is : "
								+ str);
						hndlr.obtainMessage(READ_LINE, 0, 0, str)
								.sendToTarget();
						str = "";
					} else {
						size = Bluetooth.btInputStream.read(buffer);
						Log.d("","in byte read");
						if (size > 0) {
							hndlr.obtainMessage(READ_BYTE, size, -1, buffer)
									.sendToTarget();
						}
					}
				} catch (IOException e) {
					if (!(e.toString()
							.contains("java.net.SocketTimeoutException"))) {
						tcpConnectionClose();
						Log.d("", "Bt Connection closed!!");
						return;
					}
				}
			}
		}
	}

	// **************************TCP END****************************************

}
