package com.example.tl01examen0882;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tl01examen0882.DBExamen.DbAgenda;
import com.example.tl01examen0882.DBExamen.DbContactos;
import com.example.tl01examen0882.Entidades.Contactos;
import com.example.tl01examen0882.adaptadores.ListaContactosAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = findViewById(R.id.ListaContact);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        DbContactos dbContactos= new DbContactos(MainActivity.this);

        listaArrayContactos= new ArrayList<>();

        ListaContactosAdapter adapter= new ListaContactosAdapter(dbContactos.leerContactos());
        listaContactos.setAdapter(adapter);

        /*
        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbAgenda agenda = new DbAgenda(MainActivity.this);
                SQLiteDatabase db= agenda.getWritableDatabase();

                if(db != null)
                {
                    Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(MainActivity.this, "Error en la base de datos", Toast.LENGTH_SHORT).show();
                }

            }
        }); */
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, IngresoDatos.class);
        startActivity(intent);
    }
}