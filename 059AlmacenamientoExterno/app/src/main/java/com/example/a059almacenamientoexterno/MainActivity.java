package com.example.a059almacenamientoexterno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "Documents/testfile.txt"; // Nombre del archivo de texto en almacenamiento externo
    private final String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    EditText mEditText; // Campo de texto en la interfaz de usuario para el contenido del archivo
    EditText mEditTextFileName; // Campo de texto en la interfaz de usuario para el nombre del archivo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.editText);
        mEditTextFileName = findViewById(R.id.editTextFileName);
    }

    // Método para verificar si el almacenamiento externo es escribible
    public boolean isExternalStorageWritable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    // Método para verificar si el almacenamiento externo es legible
    public boolean isExternalStorageReadable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            return true;
        }
        return false;
    }

    // Método para verificar y solicitar permisos de almacenamiento
    public void checkStoragePermission() {
        int permission = ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 101);
        }
    }

    // Método para escribir en el archivo en almacenamiento externo
    public void writeFile(View view) {
        if (isExternalStorageWritable()) {
            checkStoragePermission();
            try {
                // Crea un objeto File que representa el archivo en almacenamiento externo
                File textFile = new File(Environment.getExternalStorageDirectory(), FILENAME);

                // Crea un flujo de salida de archivo para escribir en el archivo en almacenamiento externo
                FileOutputStream fileOutputStream = new FileOutputStream(textFile);

                // Escribe el texto del campo de texto en el archivo
                fileOutputStream.write(mEditText.getText().toString().getBytes());

                // Cierra el flujo de salida de archivo
                fileOutputStream.close();

                Toast.makeText(this, "File written successfully", Toast.LENGTH_LONG).show();
            } catch (java.io.IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error writing file", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Cannot write to External Storage", Toast.LENGTH_LONG).show();
        }
    }

    // Método para leer desde el archivo en almacenamiento externo
    public void readFile(View view) {
        if (isExternalStorageReadable()) {
            checkStoragePermission();
            StringBuilder stringBuilder = new StringBuilder();
            try {
                // Crea un objeto File que representa el archivo en almacenamiento externo
                File textFile = new File(Environment.getExternalStorageDirectory(), FILENAME);

                // Crea un flujo de entrada de archivo para leer desde el archivo en almacenamiento externo
                FileInputStream fileInputStream = new FileInputStream(textFile);

                // Verifica si el flujo de entrada no es nulo
                if (fileInputStream != null) {
                    // Crea un lector de entrada para leer caracteres desde el flujo de entrada
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

                    // Crea un lector de buffer para leer líneas completas desde el lector de entrada
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String newLine = null;

                    // Lee línea por línea desde el archivo y las agrega al StringBuilder
                    while ((newLine = bufferedReader.readLine()) != null) {
                        stringBuilder.append(newLine + "\n");
                    }

                    // Cierra el flujo de entrada de archivo
                    fileInputStream.close();
                }

                // Establece el texto en el campo de texto con el contenido leído del archivo
                mEditText.setText(stringBuilder);
            } catch (java.io.IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading file", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Cannot read External Storage", Toast.LENGTH_LONG).show();
        }
    }
}