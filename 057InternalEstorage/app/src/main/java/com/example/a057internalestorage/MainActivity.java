package com.example.a057internalestorage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "testfile"; // Nombre del archivo de texto que se va a leer/escribir
    EditText mEditText; // Campo de texto en la interfaz de usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Establece el diseño de la actividad
        mEditText = findViewById(R.id.editText); // Inicializa el campo de texto mediante su ID en el diseño
    }

    // Método para escribir en el archivo
    public void writeFile(View view) {
        try {
            // Abre un flujo de salida de archivo para escribir en el archivo interno
            FileOutputStream fileOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            // Escribe el texto del campo de texto en el archivo
            fileOutputStream.write(mEditText.getText().toString().getBytes());

            // Cierra el flujo de salida de archivo
            fileOutputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer desde el archivo
    public void readFile(View view) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            // Abre un flujo de entrada de archivo para leer desde el archivo interno
            InputStream inputStream = openFileInput(FILENAME);

            // Verifica si el flujo de entrada no es nulo
            if (inputStream != null) {
                // Crea un lector de entrada para leer caracteres desde el flujo de entrada
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                // Crea un lector de buffer para leer líneas completas desde el lector de entrada
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String newLine = null;

                // Lee línea por línea desde el archivo y las agrega al StringBuilder
                while ((newLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(newLine + "\n");
                }

                // Cierra el flujo de entrada de archivo
                inputStream.close();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        // Establece el texto en el campo de texto con el contenido leído del archivo
        mEditText.setText(stringBuilder);
    }
}