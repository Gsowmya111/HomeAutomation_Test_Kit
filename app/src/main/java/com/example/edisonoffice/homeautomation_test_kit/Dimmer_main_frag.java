package com.example.edisonoffice.homeautomation_test_kit;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edisonoffice.homeautomation_test_kit.dimmerExternalLibrary.ShaderSeekArc;


public class Dimmer_main_frag extends Fragment {

    Button setings;
    ShaderSeekArc seekArc;

    ImageView i;
    Bluetooth bluetooth;
    private static final int READ_BYTE = 1;
    private static final int READ_LINE = 2;
    String f;
    Button btnH, btnM, btnL, on, off;
    TextView tv;
    String val;
    private String sendingval;
    int previous_val = 0;
    String resp_dim;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dimmer_main_frag,
                container, false);


        seekArc = (ShaderSeekArc) view.findViewById(R.id.seek_arc);
        on = (Button) view.findViewById(R.id.btnondim);
        off = (Button) view.findViewById(R.id.btnoffdim);
        btnH = (Button) view.findViewById(R.id.btn_dimhigh);
        btnL = (Button) view.findViewById(R.id.btn_dimlow);
        btnM = (Button) view.findViewById(R.id.btn_dimmedium);

        btnH.setBackgroundResource(R.drawable.dimmer_high);
        btnL.setBackgroundResource(R.drawable.dimmer_low);
        btnM.setBackgroundResource(R.drawable.dimmer_medium);




        if (bluetooth.BT_Connected) {
            bluetooth = new Bluetooth(hm);
            resp_dim="Y";
            bluetooth.btOUT("*d000#".getBytes(), true);
            Log.d("", "data send is : " + "*d000#");
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                    Toast.LENGTH_SHORT).show();
        }


        btnH.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Bluetooth.BT_Connected) {
                    bluetooth.btOUT("*D255#".getBytes(), true);
                    btnH.setBackgroundResource(R.drawable.dimmer_high01);
                    btnL.setBackgroundResource(R.drawable.dimmer_low);
                    btnM.setBackgroundResource(R.drawable.dimmer_medium);


                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Bluetooth.BT_Connected) {
                    bluetooth.btOUT("*D100#".getBytes(), true);
                    btnH.setBackgroundResource(R.drawable.dimmer_high);
                    btnL.setBackgroundResource(R.drawable.dimmer_low);
                    btnM.setBackgroundResource(R.drawable.dimmer_medium01);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Bluetooth.BT_Connected) {
                    btnH.setBackgroundResource(R.drawable.dimmer_high);
                    btnL.setBackgroundResource(R.drawable.dimmer_low01);
                    btnM.setBackgroundResource(R.drawable.dimmer_medium);
                    bluetooth.btOUT("*D025#".getBytes(), true);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Bluetooth.BT_Connected) {
                    bluetooth.btOUT("*D001#".getBytes(), true);
                    seekArc.setProgress(025);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        off.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Bluetooth.BT_Connected) {
                    bluetooth.btOUT("*D000#".getBytes(), true);


                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekArc.setOnSeekArcChangeListener(new ShaderSeekArc.OnSeekArcChangeListener() {
            @Override

            public void onProgressChanged(final ShaderSeekArc seekArc, float progress) {
                // DecimalFormat df = new DecimalFormat("#.##");
                // String formatted = df.format((String.valueOf(progress)));
                double mValue = progress;
                final long mStrippedValue = new Double(mValue).longValue();

                btnH.setBackgroundResource(R.drawable.dimmer_high);
                btnL.setBackgroundResource(R.drawable.dimmer_low);
                btnM.setBackgroundResource(R.drawable.dimmer_medium);
                resp_dim="N";

                if (seekArc.getProgress() >= 0 && seekArc.getProgress() < 127) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (seekArc.getProgress() >= 0 && seekArc.getProgress() < 25) {

                                sendingval = "020";
                                mess();

                            }

                            if (seekArc.getProgress() >= 25 && seekArc.getProgress() < 50) {

                                sendingval = "030";
                                mess();
                            }
                            if (seekArc.getProgress() >= 50 && seekArc.getProgress() < 75) {


                                sendingval = "050";
                                mess();
                            }
                            if (seekArc.getProgress() >= 75 && seekArc.getProgress() < 100) {

                                sendingval = "100";
                                mess();
                            }
                            if (seekArc.getProgress() >= 100 && seekArc.getProgress() < 127) {

                                sendingval = "120";
                                mess();
                            }

                        }
                    }, 100);
                } else if (seekArc.getProgress() >= 127 && seekArc.getProgress() <= 255) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (seekArc.getProgress() >= 127 && seekArc.getProgress() < 150) {

                                sendingval = "130";
                                mess();
                            }
                            if (seekArc.getProgress() >= 150 && seekArc.getProgress() < 175) {

                                sendingval = "150";
                                mess();

                            }
                            if (seekArc.getProgress() >= 175 && seekArc.getProgress() <= 200) {

                                sendingval = "175";
                                mess();
                            }
                            if (seekArc.getProgress() >= 200 && seekArc.getProgress() <= 225) {

                                sendingval = "200";
                                mess();
                            }
                            if (seekArc.getProgress() >= 225 && seekArc.getProgress() <= 255) {

                                sendingval = "255";
                                mess();
                            }
                        }
                    }, 200);
                }


            }

            @Override
            public void onStartTrackingTouch(ShaderSeekArc seekArc) {


            }

            @Override
            public void onStopTrackingTouch(ShaderSeekArc seekArc) {


            }
        });


        return view;
    }

    public void mess() {

        if (previous_val != Integer.parseInt(sendingval)) {
            //  String data = "AT+UCAST:000D6F001022C58C=*001000000101112" + val.toString() + "000000000000#";
            String data = "*D" + sendingval + "#";
            if (Bluetooth.BT_Connected) {
                bluetooth.btOUT(data.getBytes(), true);

            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                        Toast.LENGTH_SHORT).show();
            }
        } else {


        }
    }

    private Handler hm = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case READ_BYTE:
                    byte[] readBuf = (byte[]) msg.obj;
                    final String readMessage = new String(readBuf, 0, msg.arg1);
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {

                        }
                    });
                case READ_LINE: {
                    final String readMessage2 = (String) msg.obj;
                    Log.d("msg", "" + readMessage2 + "+" + readMessage2.length());

                    String str = readMessage2;
                    Log.d("msg", "" + str + "+" + str.length());
                    if (str.startsWith("*") && str.endsWith("@")
                            && str.length() == 5) {
                        f = str.substring(1, 4);
                        int val= Integer.parseInt(f);

                        if(resp_dim.equals("Y")){
                            resp_dim="N";
                            seekArc.setProgress(val);
                        }else{

                        }



                    }
                    break;
                }
            }
        }
    };


}
