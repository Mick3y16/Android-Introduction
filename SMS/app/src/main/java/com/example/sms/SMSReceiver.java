package com.example.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by pedro on 15/08/2016.
 */
public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Gets the text message.
        Bundle bundle = intent.getExtras();

        SmsMessage[] smsMessages = null;
        String string = "";

        if (bundle != null) {
            // Delivers the received message.
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsMessages = new SmsMessage[pdus.length];

            for (int pdu = 0; pdu < smsMessages.length; pdu++) {
                smsMessages[pdu] = SmsMessage.createFromPdu((byte[]) pdus[pdu]);

                string += "SMS from " + smsMessages[pdu].getOriginatingAddress();
                string += ": " + smsMessages[pdu].getMessageBody().toString() + "\n";
            }

            // Shows the text message.
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
        }
    }
}
