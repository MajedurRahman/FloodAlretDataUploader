package com.floodalret_datauploader;

/**
 * Created by ismail on 7/12/17.
 */
import android.content.*;
import android.os.Bundle;
import android.telephony.*;


// Todo : SMS Reciever Class

public class SimpleSmsReciever extends BroadcastReceiver {

    private static final String TAG = "Message recieved";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);
       // Log.i(TAG,  messages.getMessageBody());

        // Todo : Start Application's  MainActivty activity

        // Todo : Send Message And Number In Activity
        if (messages.getDisplayMessageBody().contains("Device Token:MQAyADUANQAMAAyAA==")){
            Intent smsIntent=new Intent(context,SMS_Receive.class);
            smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            smsIntent.putExtra("MessageNumber", messages.getOriginatingAddress());
            smsIntent.putExtra("Message", messages.getMessageBody().replace("Device Token:MQAyADUANQAMAAyAA==" , ""));
            context.startActivity(smsIntent);
        }
    }

}
