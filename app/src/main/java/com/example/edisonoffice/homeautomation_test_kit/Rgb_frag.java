package com.example.edisonoffice.homeautomation_test_kit;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.edisonoffice.homeautomation_test_kit.ColorPicker.OnColorChangedListener;
public class Rgb_frag extends Fragment  implements OnColorChangedListener{
    Bluetooth bluetooth;
    private static final int READ_BYTE = 1;
    private static final int READ_LINE = 2;
    String	f ,f1;
    private Button redBtn, greenBtn, blueBtn, orangeBtn, whiteBtn, purple,
            strobeBtn, flashBtn, fadeBtn, smoothBtn, speedUpBtn, speedDownBtn, rgbBon,rgbBoff;
    TextView speed_show;
    private ColorPicker picker;
    private SVBar svBar;
    private OpacityBar opacityBar;
    SeekBar sbr;
    public static volatile boolean speedactive = false;
    public static volatile boolean sendingRGB = false;
    int redC = 0, greenC = 0, blueC = 0;
    String rS = "", gS = "", bS = "",rrS="",ggS="",bbS="";
    int counter;
    public static volatile int r;
    int mode1;
    String rgb_resp;
   @Override
   public View onCreateView(LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.rgb_main_frag,
               container, false);


       sbr = (SeekBar) view.findViewById(R.id.brightseekBar);
     //  sbr.incrementProgressBy(1);

       picker = (ColorPicker) view.findViewById(R.id.picker);
    //   svBar = (SVBar) findViewById(R.id.svbar);
     //  opacityBar = (OpacityBar) findViewById(R.id.opacitybar);

       svBar = (SVBar) view.findViewById(R.id.svbar);
       opacityBar = (OpacityBar) view.findViewById(R.id.opacitybar);

       picker.addSVBar(svBar);
       picker.addOpacityBar(opacityBar);
       picker.addSVBar(svBar);
       picker.addOpacityBar(opacityBar);
       picker.setOnColorChangedListener(this);

       redBtn = (Button) view.findViewById(R.id.btn_red);
       greenBtn = (Button) view.findViewById(R.id.btn_green);
       blueBtn = (Button) view.findViewById(R.id.btn_blue);
       orangeBtn = (Button) view.findViewById(R.id.btn_orange);
       whiteBtn = (Button) view.findViewById(R.id.btn_white);
       purple = (Button) view.findViewById(R.id.btn_purple);
       strobeBtn = (Button) view.findViewById(R.id.btn_strobe);
       flashBtn = (Button) view.findViewById(R.id.btn_flash);
       fadeBtn = (Button) view.findViewById(R.id.btn_fade);
       smoothBtn = (Button) view.findViewById(R.id.btn_smooth);
       speedUpBtn = (Button) view.findViewById(R.id.btn_speedup);
       speedDownBtn = (Button) view.findViewById(R.id.btn_speeddown);
       rgbBon=(Button) view.findViewById(R.id.btn_rgbon);
       rgbBoff=(Button) view.findViewById(R.id.btn_rgboff);
       speed_show=(TextView) view.findViewById(R.id.tv_speedvalue);


        sbr.setMax(10);


       if (bluetooth.BT_Connected) {
           bluetooth = new Bluetooth(hm);
           rgb_resp="yes";
           bluetooth.btOUT("*Z5#".getBytes(), true);
           Log.d("", "data send is : " + "*Z5#");
       } else {
           Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                   Toast.LENGTH_SHORT).show();
       }


       rgbBon.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               if (Bluetooth.BT_Connected) {
                   speedactive = false;

                   bluetooth.btOUT("*R255000255#".getBytes(), true);


               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }


           }
       });
       rgbBoff.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               if (Bluetooth.BT_Connected) {
                   speedactive = false;

                   bluetooth.btOUT("*R000000000#".getBytes(), true);

               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }

           }
       });

       redBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   speedactive = false;
                   bluetooth.btOUT("*R255000000#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       greenBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   speedactive = false;
                   bluetooth.btOUT("*R000255000#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       blueBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                  speedactive = false;
                   bluetooth.btOUT("*R000000255#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       orangeBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                  speedactive = false;
                   bluetooth.btOUT("*R255255000#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       whiteBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   speedactive = false;
                   bluetooth.btOUT("*R255255255#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       purple.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                    speedactive = false;
                   bluetooth.btOUT("*R255000255#".getBytes(), true);
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       strobeBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                  speedactive = true;

                   strobeBtn.setBackgroundResource(R.drawable.strobe01);
                   smoothBtn.setBackgroundResource(R.mipmap.smooth);
                   flashBtn.setBackgroundResource(R.mipmap.flash);
                   fadeBtn.setBackgroundResource(R.mipmap.fade);

                   bluetooth.btOUT("*Z2#".getBytes(), true);


               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       smoothBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   speedactive = true;
                   bluetooth.btOUT("*Z4#".getBytes(), true);
                   strobeBtn.setBackgroundResource(R.mipmap.strobe);
                   smoothBtn.setBackgroundResource(R.drawable.smooth01);
                   flashBtn.setBackgroundResource(R.mipmap.flash);
                   fadeBtn.setBackgroundResource(R.mipmap.fade);

               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       flashBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   speedactive = true;
                   bluetooth.btOUT("*Z1#".getBytes(), true);
                   strobeBtn.setBackgroundResource(R.mipmap.strobe);
                   smoothBtn.setBackgroundResource(R.mipmap.smooth);
                   flashBtn.setBackgroundResource(R.drawable.flash01);
                   fadeBtn.setBackgroundResource(R.mipmap.fade);

               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       fadeBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                  speedactive = true;
                   bluetooth.btOUT("*Z3#".getBytes(), true);
                   strobeBtn.setBackgroundResource(R.mipmap.strobe);
                   smoothBtn.setBackgroundResource(R.mipmap.smooth);
                   flashBtn.setBackgroundResource(R.mipmap.flash);
                   fadeBtn.setBackgroundResource(R.drawable.fade01);

               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       final int[] count = {0};
       speedUpBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {

                    speedUpBtn.setEnabled(true);
                   speedDownBtn.setEnabled(true);
                   if (speedactive) {


                       count[0]++;
                       if (count[0] == 0) {
                           speed_show.setBackgroundResource(R.drawable.spzero);
                           speed_show.setTag(0);
                       } else if (count[0] == 1) {
                           speed_show.setBackgroundResource(R.drawable.spone);

                           speed_show.setTag(1);
                       } else if (count[0] == 2) {
                           speed_show.setBackgroundResource(R.drawable.sptwo);
                           speed_show.setTag(2);
                       } else if (count[0] == 3) {
                           speed_show.setBackgroundResource(R.drawable.spthree);
                           speed_show.setTag(3);
                       } else if (count[0] == 4) {
                           speed_show.setBackgroundResource(R.drawable.spfour);
                           speed_show.setTag(4);
                       } else if (count[0] == 5) {
                           speed_show.setBackgroundResource(R.drawable.spfive);
                           speed_show.setTag(5);
                       } else if (count[0] == 6) {
                           speed_show.setBackgroundResource(R.drawable.spsix);
                           speed_show.setTag(6);
                       } else if (count[0] == 7) {
                           speed_show.setBackgroundResource(R.drawable.spseven);
                           speed_show.setTag(7);
                       } else if (count[0] == 8) {
                           speed_show.setBackgroundResource(R.drawable.speight);
                           speed_show.setTag(8);
                       } else if (count[0] == 9) {
                           speed_show.setBackgroundResource(R.drawable.spnine);
                           speed_show.setTag(9);
                       } else if (count[0] == 10) {

                           count[0]--;
                       }





                       bluetooth.btOUT("*Y+#".getBytes(), true);
                       Log.d("", "speed up1 cmd : " + "*Y+#");
                   }
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });
       speedDownBtn.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               if (Bluetooth.BT_Connected) {
                   speedUpBtn.setEnabled(true);
                   speedDownBtn.setEnabled(true);
                   if (speedactive) {

                      count[0]--;

                       if (count[0] == -1) {
                           speedDownBtn.setEnabled(false);
                           count[0]++;
                       } else if (count[0] == 0) {
                           speed_show.setBackgroundResource(R.drawable.spzero);
                           speed_show.setTag(0);
                       } else if (count[0] == 1) {
                           speed_show.setBackgroundResource(R.drawable.spone);
                           speed_show.setTag(1);
                       } else if (count[0] == 2) {
                           speed_show.setBackgroundResource(R.drawable.sptwo);
                           speed_show.setTag(2);
                       } else if (count[0] == 3) {
                           speed_show.setBackgroundResource(R.drawable.spthree);
                           speed_show.setTag(3);
                       } else if (count[0] == 4) {
                           speed_show.setBackgroundResource(R.drawable.spfour);
                           speed_show.setTag(4);
                       } else if (count[0] == 5) {
                           speed_show.setBackgroundResource(R.drawable.spfive);
                           speed_show.setTag(5);
                       } else if (count[0] == 6) {
                           speed_show.setBackgroundResource(R.drawable.spsix);
                           speed_show.setTag(6);
                       } else if (count[0] == 7) {
                           speed_show.setBackgroundResource(R.drawable.spseven);
                           speed_show.setTag(7);
                       } else if (count[0] == 8) {
                           speed_show.setBackgroundResource(R.drawable.speight);
                           speed_show.setTag(8);
                       } else if (count[0] == 9) {
                           speed_show.setBackgroundResource(R.drawable.spnine);
                           speed_show.setTag(9);
                       }




                       bluetooth.btOUT("*Y-#".getBytes(), true);
                       Log.d("", "speed down1 cmd : " + "*Y-#");
                   }
               } else {
                   Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       sbr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {



           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {
               // TODO Auto-generated method stub

           }

           @Override
           public void onProgressChanged(SeekBar seekBar, int progress,
                                         boolean fromUser) {
               if (progress >= 0 && progress <= sbr.getMax()) {
                   if (Bluetooth.BT_Connected) {
                       speedactive=false;

                       speedDownBtn.setEnabled(true);
                       speedUpBtn.setEnabled(true);

                       if (progress >= 1&& progress<=2) {
                           bluetooth.btOUT("*X1#".getBytes(), true);
                       } else if (progress >= 2 && progress<3) {
                           bluetooth.btOUT("*X2#".getBytes(), true);
                       } else if (progress >= 3&& progress<4) {
                           bluetooth.btOUT("*X3#".getBytes(), true);
                       } else if (progress >=4&& progress<5) {
                           bluetooth.btOUT("*X4#".getBytes(), true);
                       } else if (progress >=5&& progress<6) {
                           bluetooth.btOUT("*X5#".getBytes(), true);
                       } else if (progress > 6&& progress<7) {
                           bluetooth.btOUT("*X6#".getBytes(), true);
                       } else if (progress >= 7&& progress<8) {
                           bluetooth.btOUT("*X7#".getBytes(), true);
                       } else if (progress >= 8&& progress<9) {
                           bluetooth.btOUT("*X8#".getBytes(), true);
                       } else if (progress >= 9&& progress<=10) {
                           bluetooth.btOUT("*X9#".getBytes(), true);
                       }



                   } else {
                       Toast.makeText(getActivity().getApplicationContext(), "Bt Not Connected",
                               Toast.LENGTH_SHORT).show();
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
                       getActivity().runOnUiThread(new Runnable() {
                           public void run() {
                               Toast.makeText(getActivity().getApplicationContext(), "starting command"+readMessage,
                                       Toast.LENGTH_SHORT).show();
                           }
                       });
                   case READ_LINE: {
                       final String readMessage2 = (String) msg.obj;
                       Log.d("msg", "" + readMessage2 + "+" + readMessage2.length());

                       String str = readMessage2;
                       Log.d("msg", "" + str + "+" + str.length());
                       if (str.startsWith("*") && str.endsWith("$")
                               && str.length() == 14) {
                           f = str.substring(1, 10);
                           f1 = str.substring(10, 13);


                           final String mode = str.substring(12,13);
                           final String sped = str.substring(11,12);
                           final String brig = str.substring(10,11);

                           speedDownBtn.setEnabled(true);
                           speedUpBtn.setEnabled(true);

                           getActivity().runOnUiThread(new Runnable() {
                               public void run() {

                                  if(rgb_resp.equals("yes")){
                                      rgb_resp="No";
                                      if(mode.equals("1")){
                                          flashBtn.setBackgroundResource(R.drawable.flash01);
                                          strobeBtn.setBackgroundResource(R.mipmap.strobe);
                                          fadeBtn.setBackgroundResource(R.mipmap.fade);
                                          smoothBtn.setBackgroundResource(R.mipmap.smooth);

                                      }else if(mode.equals("2")){
                                          strobeBtn.setBackgroundResource(R.drawable.strobe01);
                                          fadeBtn.setBackgroundResource(R.mipmap.fade);
                                          smoothBtn.setBackgroundResource(R.mipmap.smooth);
                                          flashBtn.setBackgroundResource(R.mipmap.flash);

                                      } if(mode.equals("3")){
                                          fadeBtn.setBackgroundResource(R.drawable.fade01);
                                          strobeBtn.setBackgroundResource(R.mipmap.strobe);
                                          smoothBtn.setBackgroundResource(R.mipmap.smooth);
                                          flashBtn.setBackgroundResource(R.mipmap.flash);
                                      } if(mode.equals("4")){
                                          smoothBtn.setBackgroundResource(R.drawable.smooth01);
                                          flashBtn.setBackgroundResource(R.mipmap.flash);
                                          strobeBtn.setBackgroundResource(R.mipmap.strobe);
                                          fadeBtn.setBackgroundResource(R.mipmap.fade);

                                      }

                                      if(brig.equals("1")){
                                          sbr.setProgress(1);
                                      }else if(brig.equals("2")){
                                          sbr.setProgress(2);
                                      }
                                      else if(brig.equals("3")){
                                          sbr.setProgress(3);
                                      }
                                      else if(brig.equals("4")){
                                          sbr.setProgress(4);
                                      }
                                      else if(brig.equals("5")){
                                          sbr.setProgress(5);
                                      }
                                      else if(brig.equals("6")){
                                          sbr.setProgress(6);
                                      }
                                      else if(brig.equals("7")){
                                          sbr.setProgress(7);
                                      }
                                      else if(brig.equals("8")){
                                          sbr.setProgress(8);
                                      }else if(brig.equals("9")){
                                          sbr.setProgress(9);
                                      }


                                      if(sped.equals("1")){
                                          speed_show.setBackgroundResource(R.drawable.spone);
                                      }else  if(sped.equals("2")){
                                          speed_show.setBackgroundResource(R.drawable.sptwo);
                                      }else  if(sped.equals("3")){
                                          speed_show.setBackgroundResource(R.drawable.spthree);
                                      }
                                      else  if(sped.equals("4")){
                                          speed_show.setBackgroundResource(R.drawable.spfour);
                                      }
                                      else  if(sped.equals("5")){
                                          speed_show.setBackgroundResource(R.drawable.spfive);
                                      }
                                      else  if(sped.equals("6")){
                                          speed_show.setBackgroundResource(R.drawable.spsix);
                                      }else  if(sped.equals("7")){
                                          speed_show.setBackgroundResource(R.drawable.spseven);
                                      }else  if(sped.equals("8")){
                                          speed_show.setBackgroundResource(R.drawable.speight);
                                      }
                                      else  if(sped.equals("9")){
                                          speed_show.setBackgroundResource(R.drawable.spnine);
                                      }
                                      else  if(sped.equals("0")){
                                          speed_show.setBackgroundResource(R.drawable.spzero);
                                      }




                                  }

                                 //  Toast.makeText(getActivity().getApplicationContext(), "RGB status"+mode, Toast.LENGTH_SHORT).show();
                               }
                           });



                       }
                       break;
                   }
               }
           }
       };


    @Override
    public void onColorChanged(final int color) {
        // gives the color when it's changed.
        if(!sendingRGB){

            flashBtn.setBackgroundResource(R.mipmap.flash);
            strobeBtn.setBackgroundResource(R.mipmap.strobe);
            fadeBtn.setBackgroundResource(R.mipmap.fade);
            smoothBtn.setBackgroundResource(R.mipmap.smooth);
            speedDownBtn.setEnabled(false);
            speedUpBtn.setEnabled(false);
            speed_show.setBackgroundResource(R.drawable.spzero);

            Thread timer = new Thread() {
                public void run(){
                    try {
                        sendingRGB = true;
                        redC = Color.red(color);
                        greenC = Color.green(color);
                        blueC = Color.blue(color);
                        int a = Color.alpha(color);

                        rS = "" + redC;
                        while (rS.length() < 3) {
                            rS = "0" + rS;
                            Log.d("", "red : " + rS);
                        }

                        gS = "" + greenC;
                        //Log.d("", "green1 : " + gS);
                        while (gS.length() < 3) {
                            gS = "0" + gS;
                            Log.d("", "green : " + gS);
                        }

                        bS = "" + blueC;
                        //Log.d("", "blue1 : " + bS);
                        while (bS.length() < 3) {
                            bS = "0" + bS;
                            Log.d("", "blue : " + bS);
                        }

                        String str1 = "*R" + rS + gS + bS + "#";
                        if(str1.length()==12)
                            bluetooth.btOUT(str1.getBytes(), true);
                        sleep(200);
                        speedactive = false;
                        sendingRGB = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            timer.start();

        }


    }


}
