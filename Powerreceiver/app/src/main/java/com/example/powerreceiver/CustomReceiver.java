package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
      String intentAction= intent.getAction();
      String toastmessage=null;
      if(intentAction !=null){
          toastmessage="unknown intent action";
          switch (intentAction){
              case Intent.ACTION_POWER_CONNECTED:
                  toastmessage="Power connected!";
                  break;
              case Intent.ACTION_POWER_DISCONNECTED:
                  toastmessage="Power diconnected!";
                  break;
          }
          Toast.makeText(context,toastmessage,Toast.LENGTH_LONG).show();
      }
    }
}