package com.example.a068sendsms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.telephony.SmsManager;

public class MainActivity extends AppCompatActivity implements ReceptorSMS.onRecibeSMS {

    private ReceptorSMS receptorSMS;
    private EditText editTextNumeroRecibido;
    private EditText editTextMensajeRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receptorSMS = new ReceptorSMS();
        receptorSMS.setOnRecibeSMSListener(this);

        // Registrar el receptor de SMS
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receptorSMS, filter);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::EnviarSMS);

        // Inicializar los EditText para mostrar el mensaje y el número recibido
        editTextNumeroRecibido = findViewById(R.id.editTextNumero);
        editTextMensajeRecibido = findViewById(R.id.mensaje);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receptorSMS);
    }

    @Override
    public void onRecibeSMS(String origen, String mensaje) {
        // Manejar el mensaje recibido
        Log.d("MainActivity", "SMS de: " + origen + ", Mensaje: " + mensaje);
        runOnUiThread(() -> {
            Toast.makeText(this, "SMS de: " + origen + ", Mensaje: " + mensaje, Toast.LENGTH_LONG).show();
            editTextNumeroRecibido.setText(origen);
            editTextMensajeRecibido.setText(mensaje);
        });
    }

    public void EnviarSMS(View v) {
        EditText txtTelefono = findViewById(R.id.editTextNumero);
        EditText txtMensaje = findViewById(R.id.mensaje);
        Log.i("OJO", "Enviando SMS ....");
        String telefono = txtTelefono.getText().toString();
        String message = txtMensaje.getText().toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefono, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS enviado.", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS no enviado, por favor, inténtalo otra vez.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
