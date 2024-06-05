package com.example.a058almacenamientofichero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText mEditText; // Campo de texto en la interfaz de usuario para el contenido del archivo
    EditText mEditTextFileName; // Campo de texto en la interfaz de usuario para el nombre del archivo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa los elementos de la interfaz de usuario
        mEditText = findViewById(R.id.editText);
        mEditTextFileName = findViewById(R.id.editTextFileName);

        // Configura los botones para llamar a los métodos correspondientes
        Button buttonWrite = findViewById(R.id.buttonWrite);
        buttonWrite.setOnClickListener(view -> writeFile(view));

        Button buttonRead = findViewById(R.id.buttonRead);
        buttonRead.setOnClickListener(view -> readFile(view));
    }

    // Método para escribir en el archivo en almacenamiento interno
    public void writeFile(View view) {
        String fileName = mEditTextFileName.getText().toString(); // Obtiene el nombre del archivo desde el EditText
        try {
            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);

            // Escribe el texto del campo de texto en el archivo
            fileOutputStream.write(mEditText.getText().toString().getBytes());

            // Cierra el flujo de salida de archivo
            fileOutputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer desde el archivo en almacenamiento interno
    public void readFile(View view) {
        String fileName = mEditTextFileName.getText().toString(); // Obtiene el nombre del archivo desde el EditText
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = openFileInput(fileName);

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