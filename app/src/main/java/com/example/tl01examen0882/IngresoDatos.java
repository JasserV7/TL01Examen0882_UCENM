package com.example.tl01examen0882;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tl01examen0882.DBExamen.DbContactos;

public class IngresoDatos extends AppCompatActivity {


    EditText txtNombre, txtTelefono, txtNota, txtPais;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_datos);


        txtPais= (EditText) findViewById(R.id.txtPaises);
        txtNombre= (EditText) findViewById(R.id.txtNombre);
        txtTelefono=(EditText) findViewById(R.id.txtTelefono);
        txtNota= (EditText) findViewById(R.id.txtNota);
        btnGuardar= (Button) findViewById(R.id.btnGuardar);




        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Validar()){

                    DbContactos dbContactos= new DbContactos(IngresoDatos.this);
                    long id = dbContactos.insertarContacto(txtPais.getText().toString(),txtNombre.getText().toString(), txtTelefono.getText().toString(), txtNota.getText().toString());

                    if(id>0)
                    {
                        Toast.makeText(IngresoDatos.this,"Registro Guardado",Toast.LENGTH_SHORT).show();
                        LimpiarPantalla();
                    }else
                    {
                        Toast.makeText(IngresoDatos.this, "Registro No guardado ocurrio un error", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(IngresoDatos.this, "Complete los campos.", Toast.LENGTH_SHORT).show();
                }


               /* DbContactos dbContactos= new DbContactos(IngresoDatos.this);
               long id = dbContactos.insertarContacto(txtPais.getText().toString(),txtNombre.getText().toString(), txtTelefono.getText().toString(), txtNota.getText().toString());

                if(id>0)
                {
                    Toast.makeText(IngresoDatos.this,"Registro Guardado",Toast.LENGTH_SHORT).show();
                    LimpiarPantalla();
                }else
                {
                    Toast.makeText(IngresoDatos.this, "Registro No guardado ocurrio un error", Toast.LENGTH_SHORT).show();
                } */

            }
        });
    }

    private  void LimpiarPantalla(){
        txtPais.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtNota.setText("");
    }

    public boolean Validar(){
        boolean retorno= true;

        String campo1 = txtPais.getText().toString();
        String campo2 = txtNombre.getText().toString();
        String campo3 = txtTelefono.getText().toString();
        String campo4 = txtNota.getText().toString();

        if(campo1.isEmpty())
        {
            txtPais.setError("El campo Pais no puede quedar vacio");
            retorno = false;
        }
        if(campo2.isEmpty())
        {
            txtNombre.setError("El campo Nombre no puede quedar vacio");
            retorno = false;
        }
        if(campo3.isEmpty())
        {
            txtTelefono.setError("El campo Telefono no puede quedar vacio");
            retorno = false;
        }
        if(campo4.isEmpty())
        {
            txtNota.setError("El campo Nota no puede quedar vacio");
            retorno = false;
        }

        return retorno;
    }


}