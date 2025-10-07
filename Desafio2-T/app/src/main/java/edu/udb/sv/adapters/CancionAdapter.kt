package edu.udb.sv.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.udb.sv.R
import edu.udb.sv.models.Cancion

class CancionAdapter(
    private var canciones: List<Cancion>
) : RecyclerView.Adapter<CancionAdapter.CancionViewHolder>() {

    class CancionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvArtista: TextView = itemView.findViewById(R.id.tvArtista)
        val tvDuracion: TextView = itemView.findViewById(R.id.tvDuracion)
        val tvGenero: TextView = itemView.findViewById(R.id.tvGenero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cancion, parent, false)
        return CancionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CancionViewHolder, position: Int) {
        val cancion = canciones[position]

        holder.tvTitulo.text = cancion.titulo
        holder.tvArtista.text = "Artista: ${cancion.nombreArtista}"
        holder.tvDuracion.text = "Duración: ${cancion.duracion}"
        holder.tvGenero.text = "Género: ${cancion.genero}"
    }

    override fun getItemCount(): Int = canciones.size

    fun actualizarLista(nuevaLista: List<Cancion>) {
        canciones = nuevaLista
        notifyDataSetChanged()
    }
}