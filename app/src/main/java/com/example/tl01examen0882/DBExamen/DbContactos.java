package com.example.tl01examen0882.DBExamen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tl01examen0882.Entidades.Contactos;

import java.util.ArrayList;

public class DbContactos extends DbAgenda{


    Context context;


    public DbContactos(@Nullable Context context) {
        super(context);
        this.context= context;
    }

    public long insertarContacto(String Pais, String Nombre, String Telefono, String Nota)
    {

        long id= 0;
        try {
            DbAgenda agenda = new DbAgenda(context);
            SQLiteDatabase db = agenda.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Pais", Pais);
            values.put("Nombre", Nombre);
            values.put("Telefono", Telefono);
            values.put("Nota", Nota);

            id = db.insert(TABLE_CONTACTOS, null, values);


        }catch (Exception ex)
        {
            ex.toString();
        }

        return id;

    }

    public ArrayList<Contactos> leerContactos(){
        DbAgenda agenda = new DbAgenda(context);
        SQLiteDatabase db = agenda.getWritableDatabase();

        ArrayList<Contactos> listaContactos= new ArrayList<>();
        Contactos contactos;
        Cursor cursorContactos;

        cursorContactos= db.rawQuery("SELECT * FROM " +TABLE_CONTACTOS, null);

        if(cursorContactos.moveToFirst())
        {
            do
            {
                contactos= new Contactos();
                contactos.setId(cursorContactos.getInt(0));
                contactos.setPais(cursorContactos.getString(1));
                contactos.setNombre(cursorContactos.getString(2));
                contactos.setTelefono(cursorContactos.getString(3));
                contactos.setNota(cursorContactos.getString(4));
                listaContactos.add(contactos);

            }while(cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }

    public Contactos verContactos(int id){
        DbAgenda agenda = new DbAgenda(context);
        SQLiteDatabase db = agenda.getWritableDatabase();


        Contactos contactos= null;
        Cursor cursorContactos;

        cursorContactos= db.rawQuery("SELECT * FROM " +TABLE_CONTACTOS+" WHERE id ="+id+" LIMIT 1 ",null);

        if(cursorContactos.moveToFirst())
        {
              contactos= new Contactos();
              contactos.setId(cursorContactos.getInt(0));
              contactos.setPais(cursorContactos.getString(1));
              contactos.setNombre(cursorContactos.getString(2));
              contactos.setTelefono(cursorContactos.getString(3));
              contactos.setNota(cursorContactos.getString(4));

        }

        cursorContactos.close();

        return contactos;
    }


    public boolean editarContacto(int id, String Pais, String Nombre, String Telefono, String Nota)
    {

        boolean correcto= false;
        DbAgenda agenda = new DbAgenda(context);
        SQLiteDatabase db = agenda.getWritableDatabase();

        try {

            db.execSQL("UPDATE "+TABLE_CONTACTOS+" SET Pais='"+Pais+"',Nombre ='"+Nombre+"',Telefono='"+Telefono+"',Nota='"+Nota+"'WHERE id='"+id+"'");
            correcto= true;

        }catch (Exception ex)
        {
            ex.toString();
            correcto= false;
        } finally {
            db.close();
        }

        return correcto;

    }

    public boolean eliminarContacto(int id)
    {

        boolean correcto= false;
        DbAgenda agenda = new DbAgenda(context);
        SQLiteDatabase db = agenda.getWritableDatabase();

        try {

            db.execSQL(" DELETE FROM "+TABLE_CONTACTOS+ " WHERE id='"+id+"'");
            correcto= true;

        }catch (Exception ex)
        {
            ex.toString();
            correcto= false;
        } finally {
            db.close();
        }

        return correcto;

    }
}
