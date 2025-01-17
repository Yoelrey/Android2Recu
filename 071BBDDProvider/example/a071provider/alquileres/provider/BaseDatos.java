package com.example.a071provider.alquileres.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * Clase auxiliar para controlar accesos a la base de datos SQLite
 */
public class BaseDatos extends SQLiteOpenHelper {

    static final int VERSION = 1;

    static final String NOMBRE_BD = "alquileres.db";


    interface Tablas {
        String APARTAMENTO = "alquiler";
    }

    public BaseDatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + Tablas.APARTAMENTO + "("
                        + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Contrato.Alquileres.ID_ALQUILER + " TEXT UNIQUE NOT NULL,"
                        + Contrato.Alquileres.NOMBRE + " TEXT NOT NULL,"
                        + Contrato.Alquileres.UBICACION + " TEXT NOT NULL,"
                        + Contrato.Alquileres.DESCRIPCION + " TEXT NOT NULL,"
                        + Contrato.Alquileres.PRECIO + " REAL NOT NULL,"
                        + Contrato.Alquileres.URL_IMAGEN + " TEXT NOT NULL)");

        // Registro ejemplo #1
        ContentValues valores = new ContentValues();
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "Inmejorable vivienda en Cali");
        valores.put(Contrato.Alquileres.UBICACION, "Cali");
        valores.put(Contrato.Alquileres.DESCRIPCION, "Apartamento amplio, cerca a centros comerciales, garaje");
        valores.put(Contrato.Alquileres.PRECIO, "200");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto1.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        // Registro ejemplo #2
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "3 habitaciones en Villa Real");
        valores.put(Contrato.Alquileres.UBICACION, "Barranquilla");
        valores.put(Contrato.Alquileres.DESCRIPCION, "Casa buena, bonita y barata");
        valores.put(Contrato.Alquileres.PRECIO, "240");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto2.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        // Registro ejemplo #3
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "Calle la dorada en Palmira");
        valores.put(Contrato.Alquileres.UBICACION, "Palmira");
        valores.put(Contrato.Alquileres.DESCRIPCION, "Apartamento en buen barrio, iglesia cercana, dos pisos");
        valores.put(Contrato.Alquileres.PRECIO, "300");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto3.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        // Registro ejemplo #4
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "2 habitaciones en Nápoles");
        valores.put(Contrato.Alquileres.UBICACION, "Cali");
        valores.put(Contrato.Alquileres.DESCRIPCION, "Apartamento recien terminado, con terraza, 2 baños");
        valores.put(Contrato.Alquileres.PRECIO, "325");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto4.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        // Registro ejemplo #5
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "Calle Sparta, Manizales");
        valores.put(Contrato.Alquileres.UBICACION, "Manizales");
        valores.put(Contrato.Alquileres.DESCRIPCION, "Barrio la Ceremonia, 3er piso + terraza");
        valores.put(Contrato.Alquileres.PRECIO, "200");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto5.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        // Registro ejemplo #6
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "Calle La Costa - Lechería");
        valores.put(Contrato.Alquileres.UBICACION, "Barranquilla");
        valores.put(Contrato.Alquileres.DESCRIPCION, "86m2 en ubicación privilegiada");
        valores.put(Contrato.Alquileres.PRECIO, "500");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto6.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        // Registro ejemplo #7
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "Manzanare Norte - Manzanares - Caracas - Baruta (sur)");
        valores.put(Contrato.Alquileres.UBICACION, "Cali");
        valores.put(Contrato.Alquileres.DESCRIPCION, "Parqueadero, piscina, vigilancia privada");
        valores.put(Contrato.Alquileres.PRECIO, "540");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto7.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        // Registro ejemplo #8
        valores.put(Contrato.Alquileres.ID_ALQUILER, Contrato.Alquileres.generarIdAlquiler());
        valores.put(Contrato.Alquileres.NOMBRE, "Casa, alquiler");
        valores.put(Contrato.Alquileres.UBICACION, "Cali");
        valores.put(Contrato.Alquileres.DESCRIPCION, "Las Granjas, 2alcobas, 2baños,...");
        valores.put(Contrato.Alquileres.PRECIO, "310");
        valores.put(Contrato.Alquileres.URL_IMAGEN, "https://www.develou.com/wp-content/uploads/2016/01/apto8.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.APARTAMENTO);
        } catch (SQLiteException e) {
            // Manejo de excepciones
        }
        onCreate(db);
    }
}
