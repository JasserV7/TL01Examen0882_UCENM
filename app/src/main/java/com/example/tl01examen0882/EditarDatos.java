package com.example.tl01examen0882;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tl01examen0882.DBExamen.DbContactos;
import com.example.tl01examen0882.Entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarDatos extends AppCompatActivity {

    EditText txtPais, txtNombre, txtTelefono, txtNota;
    Button btnGuardar;

    FloatingActionButton fabEditar, fabEliminar;

    boolean correcto= false;

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
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar= findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

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

        final DbContactos dbContactos= new DbContactos(EditarDatos.this);
        contacto= dbContactos.verContactos(id);

        if(contacto != null)
        {
            txtPais.setText(contacto.getPais());
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtNota.setText(contacto.getNota());


        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPais.getText().toString().equals("") && !txtNombre.getText().toString().equals("") &&
                        !txtTelefono.getText().toString().equals("") && !txtNota.getText().toString().equals(""))
                {
                   correcto= dbContactos.editarContacto(id,txtPais.getText().toString(),txtNombre.getText().toString(),txtTelefono.getText().toString(),txtNota.getText().toString());

                   if(correcto)
                   {
                       Toast.makeText(EditarDatos.this, "Datos actualizados", Toast.LENGTH_SHORT).show();
                       verRegistro();
                   }else{
                       Toast.makeText(EditarDatos.this, "Los datos no se pudieron actualizar ", Toast.LENGTH_SHORT).show();
                   }
                }else
                {
                    Toast.makeText(EditarDatos.this, "Debe llenar todos los campos de la pantalla", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verRegistro()
    {
        Intent intent= new Intent(this, VerDatos.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
