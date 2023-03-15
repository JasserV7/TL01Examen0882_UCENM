package com.example.tl01examen0882.DBExamen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbAgenda extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION=1;
    private static  final String DATABASE_NOMBRE="agenda.db";
    public static  final String TABLE_CONTACTOS="t_contact";



    public DbAgenda(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Pais TEXT," +
                "Nombre TEXT," +
                "Telefono TEXT," +
                "Nota TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        onCreate(sqLiteDatabase);


    }
}
