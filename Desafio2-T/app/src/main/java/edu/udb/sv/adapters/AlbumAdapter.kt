package edu.udb.sv.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.udb.sv.R
import edu.udb.sv.models.Album

class AlbumAdapter(
    private var albumes: List<Album>
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvArtista: TextView = itemView.findViewById(R.id.tvArtista)
        val tvAno: TextView = itemView.findViewById(R.id.tvAno)
        val tvGenero: TextView = itemView.findViewById(R.id.tvGenero)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumes[position]

        holder.tvTitulo.text = album.titulo
        holder.tvArtista.text = "Artista: ${album.nombreArtista}"
        holder.tvAno.text = "Año: ${album.año}"
        holder.tvGenero.text = "Género: ${album.genero}"
    }

    override fun getItemCount(): Int = albumes.size

    fun actualizarLista(nuevaLista: List<Album>) {
        albumes = nuevaLista
        notifyDataSetChanged()
    }
}