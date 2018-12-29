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
import android.widget.Toast;

import java.io.IOException;

import static com.example.edisonoffice.homeautomation_test_kit.Bluetooth.btSocket;

public class Fragmentswitch5plus1 extends Fragment {

    ImageView fansped;
    Button bulb1,bulb2,bulb3,bulb4,bulb5,fan_up,fan_down,fan_on,fan_off,bulb_allon,bulb_alloff;
    Bluetooth bluetooth;
    public static volatile int active = 0;
    private static final int READ_BYTE = 1;
    private static final int READ_LINE = 2;
    public static volatile String str = "";
    public static volatile String current_sts = "";
    String s, s1, s2, s3, s4,s5;
    int counter;
    String f;
    public static volatile int r;
    String fandata;
    int Tag;
   @Override
   public View onCreateView(LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_switch_board_5plus1,
               container, false);

       bulb1= (Button) view.findViewById(R.id.btnbulb1);
       bulb2= (Button) view.findViewById(R.id.btnbulb2);
       bulb3= (Button) view.findViewById(R.id.btnbulb3);
       bulb4= (Button) view.findViewById(R.id.btnbulb4);
       bulb5= (Button) view.findViewById(R.id.btnbulb5);
       fan_up= (Button) view.findViewById(R.id.btnfanup1);
       fan_down= (Button) view.findViewById(R.id.btnfandwn1);
       fan_on= (Button) view.findViewById(R.id.fan);
       bulb_allon= (Button) view.findViewById(R.id.btnallon);
       bulb_alloff= (Button) view.findViewById(R.id.btnalloff);
       fansped= (ImageView) view.findViewById(R.id.img_leddisp1);
       fan_on.setTag(0);
       fandata="0";

       if ((Static_variables.mac_id).length() == 17 && bluetooth.BT_Connected) {
           bluetooth = new Bluetooth(hm);
           bluetooth.btOUT("*S0#".getBytes(), true);
           try {
               Thread.sleep(1000);
               bluetooth.btOUT("*f0#".getBytes(), true);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           Log.d("","data send is : "+"*S0#");
       } else {
           Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                   Toast.LENGTH_SHORT).show();
       }

       bulb_allon.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   bluetooth.btOUT("*C1#".getBytes(), true);
                   Log.d("", "bulb on "+"*C1#");
               }

               else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       bulb_alloff.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   bluetooth.btOUT("*C0#".getBytes(), true);
                   Log.d("", "bulb off "+"*C0#");
               }

               else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       bulb1.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               if (Bluetooth.BT_Connected) {
                   bluetooth.btOUT("*S1#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }


           }
       });

       bulb2.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View arg0) {
               if (Bluetooth.BT_Connected) {
                   bluetooth.btOUT("*S2#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }

           }
       });
       bulb3.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View arg0) {
               // TODO Auto-generated method stub
               if (Bluetooth.BT_Connected) {
                   bluetooth.btOUT("*S3#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       bulb4.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View arg0) {
               // TODO Auto-generated method stub
               if (Bluetooth.BT_Connected) {
                   bluetooth.btOUT("*S4#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       bulb5.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View arg0) {
               // TODO Auto-generated method stub
               if (Bluetooth.BT_Connected) {
                   bluetooth.btOUT("*S5#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       fan_on.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               if (bluetooth.BT_Connected) {

                   bluetooth.btOUT("*F0#".getBytes(), true);
           //    fan_on.setTag(1);


                   if(fandata.equals("0")){
                       fan_on.setBackgroundResource(R.drawable.fan02);
                      // break;
                      fandata="1";
                   }else {
                       fan_on.setBackgroundResource(R.drawable.fan01);
                       fandata="0";
                   }


                  /* if(fan_on.getTag().toString().equals("0")){
                       fan_on.setTag(1);

                       fan_on.setBackgroundResource(R.drawable.fan01);

                   }else if(fan_on.getTag().toString().equals("1")){
                       fan_on.setTag(0);

                       fan_on.setBackgroundResource(R.drawable.fan02);

                   }*/

               } else {
                   Toast.makeText(getActivity().getApplication(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }

           }
       });


       fan_up.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               if (counter < 9) {
                   counter++;
                   r = counter;

                   if (counter == 1) {
                       bluetooth.btOUT("*F1#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.one);
                   } else if (counter == 2) {
                       bluetooth.btOUT("*F2#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.two);
                   } else if (counter == 3) {
                       bluetooth.btOUT("*F3#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.three);
                   } else if (counter == 4) {
                       bluetooth.btOUT("*F4#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.four);

                   }
               }
           }
       });


       fan_down.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               if (counter > 1) {
                   counter--;

                   r = counter;

                   if (counter == 1) {
                       bluetooth.btOUT("*F1#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.one);
                   } else if (counter == 2) {
                       bluetooth.btOUT("*F2#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.two);
                   } else if (counter == 3) {
                       bluetooth.btOUT("*F3#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.three);
                   } else if (counter == 4) {
                       bluetooth.btOUT("*F4#".getBytes(), true);
                       fansped.setBackgroundResource(R.drawable.four);
                   }
               }
           }
       });


       return view;

   }


    private Handler hm = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case READ_BYTE:
                    byte[] readBuf = (byte[]) msg.obj;
                    final String readMessage = new String(readBuf, 0, msg.arg1);
                   /* runOnUiThread(new Runnable() {
                        public void run() {
                            // tv1.setText(readMessage);
                        }
                    });*/
                case READ_LINE:
                    final String readMessage2 = (String) msg.obj;

                    Log.d("msg", "" + readMessage2 + "+" + readMessage2.length());
                    str = readMessage2;

                    Log.d("msg", "" + str + "+" + str.length());
                    if (str.startsWith("*") && str.endsWith("#")
                            && str.length() == 8) {
                        current_sts = str;
                        s = str.substring(1, 5);
                        s1 = str.substring(1, 2);
                        s2 = str.substring(2, 3);
                        s3 = str.substring(3, 4);
                         s4=str.substring(4, 5);
                        s5=str.substring(5, 6);

                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                if (s1.equals("0")) {

                                    bulb1.setBackgroundResource(R.drawable.bulb01);

                                } else if (s1.equals("1")) {
                                    bulb1.setBackgroundResource(R.drawable.bulb02);

                                }
                                if (s2.equals("0")) {

                                    bulb2.setBackgroundResource(R.drawable.bulb01);

                                } else if (s2.equals("1")) {
                                    bulb2.setBackgroundResource(R.drawable.bulb02);

                                }
                                if (s3.equals("0")) {

                                    bulb3.setBackgroundResource(R.drawable.bulb01);

                                } else if (s3.equals("1")) {
                                    bulb3.setBackgroundResource(R.drawable.bulb02);

                                }
                                if (s4.equals("0")) {

                                    bulb4.setBackgroundResource(R.drawable.bulb01);

                                } else if (s4.equals("1")) {
                                    bulb4.setBackgroundResource(R.drawable.bulb02);

                                }
                                if (s5.equals("0")) {

                                    bulb5.setBackgroundResource(R.drawable.bulb01);

                                } else if (s5.equals("1")) {
                                    bulb5.setBackgroundResource(R.drawable.bulb02);

                                }






                            }
                        });

                        str = "";
                    }else 	if (str.startsWith("*") && str.endsWith("#")
                            && str.length() == 4) {
                        f = str.substring(1, 3);

                        Log.d("msg", "" + f);

                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {

                                if (f.equals("01")) {
                                    r = 1;
                                    counter = 1;
                                    fansped.setBackgroundResource(R.drawable.one);
                                    fan_up.setEnabled(false);
                                    fan_down.setEnabled(false);
                                } else if (f.equals("11")) {
                                    r = 1;
                                    counter = 1;

                                    fansped.setBackgroundResource(R.drawable.one);
                                    fan_up.setEnabled(true);
                                    fan_down.setEnabled(true);
                                    fan_on.setBackgroundResource(R.drawable.fan02);
                                    // break;
                                    fandata="1";

                                }
                                if (f.equals("02")) {
                                    r = 2;
                                    counter = 2;
                                    fansped.setBackgroundResource(R.drawable.two);
                                    fan_up.setEnabled(false);
                                    fan_down.setEnabled(false);
                                } else if (f.equals("12")) {
                                    r = 2;
                                    counter = 2;
                                    fansped.setBackgroundResource(R.drawable.two);
                                    fan_up.setEnabled(true);
                                    fan_down.setEnabled(true);
                                    fan_on.setBackgroundResource(R.drawable.fan02);
                                    // break;
                                    fandata="1";
                                }
                                if (f.equals("03")) {
                                    r = 3;
                                    counter = 3;
                                    fansped.setBackgroundResource(R.drawable.three);
                                    fan_up.setEnabled(false);
                                    fan_down.setEnabled(false);
                                } else if (f.equals("13")) {
                                    r = 3;
                                    counter = 3;
                                    fansped.setBackgroundResource(R.drawable.three);
                                    fan_up.setEnabled(true);
                                    fan_down.setEnabled(true);
                                    fan_on.setBackgroundResource(R.drawable.fan02);
                                    // break;
                                    fandata="1";
                                }
                                if (f.equals("04")) {
                                    r = 4;
                                    counter = 4;
                                    fansped.setBackgroundResource(R.drawable.four);
                                    fan_up.setEnabled(false);
                                    fan_down.setEnabled(false);

                                } else if (f.equals("14")) {
                                    r = 4;
                                    counter = 4;
                                    fan_on.setBackgroundResource(R.drawable.fan02);
                                    fandata="1";
                                    fansped.setBackgroundResource(R.drawable.four);
                                    fan_up.setEnabled(true);
                                    fan_down.setEnabled(true);
                                }

                            }
                        });

                    }

                    break;

            }
        }
    };


    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "on pause1");

        if(bluetooth.BT_Connected)
            bluetooth.hndlr.removeCallbacksAndMessages(null);

        active = 1;

    }

   /* @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (active == 1) {
            if (bluetooth.BT_Connected) {
                bluetooth = new Bluetooth(hm);
                bluetooth.btOUT("*S0#".getBytes(), true);
            } else {
                Toast.makeText(getApplicationContext(), "Bt Not Connected",
                        Toast.LENGTH_SHORT).show();
            }
            active = 0;
        }
*/





}
