package com.example.tl01examen0882;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tl01examen0882.DBExamen.DbContactos;
import com.example.tl01examen0882.Entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerDatos extends AppCompatActivity {

    EditText txtPais, txtNombre, txtTelefono, txtNota;
    Button btnGuardar;

    FloatingActionButton fabEditar, fabEliminar, fabLlamada, fabCompartir;

    Contactos contacto;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_datos);

        txtPais= findViewById(R.id.txtPaises);
        txtNombre= findViewById(R.id.txtNombre);
        txtTelefono= findViewById(R.id.txtTelefono);
        txtNota= findViewById(R.id.txtNota);
        btnGuardar= findViewById(R.id.btnGuardar);
        fabEditar= findViewById(R.id.fabEditar);
        fabEliminar= findViewById(R.id.fabEliminar);
        fabLlamada= findViewById(R.id.fabLlamada);
        fabCompartir= findViewById(R.id.fCompartir);


        if(savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();

            if(extras == null)
            {
                id= Integer.parseInt(null);
            }else
            {
                id= extras.getInt("ID");
            }
        } else
        {
            id= (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos= new DbContactos(VerDatos.this);
        contacto= dbContactos.verContactos(id);

        if(contacto != null)
        {
            txtPais.setText(contacto.getPais());
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtNota.setText(contacto.getNota());

            btnGuardar.setVisibility(View.INVISIBLE);

            txtPais.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtNota.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(VerDatos.this,EditarDatos.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(VerDatos.this);
                builder.setMessage("¿Desea eliminar este contacto de su agenda?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(dbContactos.eliminarContacto(id)){
                            lista();
                        }

                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });


        fabLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(VerDatos.this);
                builder.setMessage("¿Desea llamar a esta persona? ").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /* ponemos el codigo para la llamda*/

                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+ txtTelefono.getText().toString() ));
                        startActivity(intent);

                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                /* Cuando no queremos llamar*/

                            }
                        }).show();

            }
        });

        fabCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "telefono de: "+txtNombre.getText().toString() + txtTelefono.getText().toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });


    }


    private  void lista(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}