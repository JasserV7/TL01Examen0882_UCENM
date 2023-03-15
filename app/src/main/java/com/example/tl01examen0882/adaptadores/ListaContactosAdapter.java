package com.example.tl01examen0882.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tl01examen0882.Entidades.Contactos;
import com.example.tl01examen0882.R;
import com.example.tl01examen0882.VerDatos;

import java.util.ArrayList;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    public ListaContactosAdapter(ArrayList<Contactos> listaContactos){
        this.listaContactos= listaContactos;
    }
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto,null,false);

        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.ViewPais.setText(listaContactos.get(position).getPais());
        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewNota.setText(listaContactos.get(position).getNota());

    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView ViewPais, viewNombre, viewTelefono, viewNota;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            ViewPais= itemView.findViewById(R.id.ViewPais);
            viewNombre= itemView.findViewById(R.id.viewNombre);
            viewTelefono= itemView.findViewById(R.id.viewTelefono);
            viewNota= itemView.findViewById(R.id.viewNota);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context= view.getContext();
                    Intent intent = new Intent(context, VerDatos.class);
                    intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
