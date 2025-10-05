package edu.udb.sv.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.udb.sv.R
import edu.udb.sv.models.Artista

class ArtistaAdapter(
    private var artistas: List<Artista>
) : RecyclerView.Adapter<ArtistaAdapter.ArtistaViewHolder>() {

    class ArtistaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvGenero: TextView = itemView.findViewById(R.id.tvGenero)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artista, parent, false)
        return ArtistaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistaViewHolder, position: Int) {
        val artista = artistas[position]

        holder.tvNombre.text = artista.nombre
        holder.tvGenero.text = "Género: ${artista.genero}"
        holder.tvDescripcion.text = artista.descripcion
    }

    override fun getItemCount(): Int = artistas.size

    fun actualizarLista(nuevaLista: List<Artista>) {
        artistas = nuevaLista
        notifyDataSetChanged()
    }
}