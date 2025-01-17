package com.example.a068sendsms;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceptorSMS extends BroadcastReceiver {

    private final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    // Interfaz (listener) para comunicarnos con la actividad que instancia un BroadcastReceiver
    public interface onRecibeSMS {
        void onRecibeSMS(String origen, String msg);
    }

    private onRecibeSMS respuesta;

    public void setOnRecibeSMSListener(onRecibeSMS listener) {
        respuesta = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            // Obtener el mensaje de SMS
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String origen = null;
            String msg = null;

            if (bundle != null) {
                // Obtener el mensaje original SMS
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    msgs = new SmsMessage[pdus.length];

                    for (int i = 0; i < pdus.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        origen = msgs[i].getOriginatingAddress();
                        msg = msgs[i].getMessageBody();

                        if (respuesta != null) {
                            respuesta.onRecibeSMS(origen, msg);
                        }

                        // Usar un Handler para mostrar el Toast en el hilo principal
                        new Handler(Looper.getMainLooper()).post(() ->
                                Toast.makeText(context, "SMS Recibido!", Toast.LENGTH_LONG).show()
                        );
                    }
                }
            }
        }
    }
}
