package edu.udb.sv

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.udb.sv.adapters.AlbumAdapter
import edu.udb.sv.databinding.ActivityAlbumesBinding
import edu.udb.sv.db.DatabaseHelper

class AlbumesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumesBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var albumAdapter: AlbumAdapter
    private var listaAlbumes = emptyList<edu.udb.sv.models.Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("DEBUG_ALBUMES", "=== INICIANDO ALBUMESACTIVITY ===")

        try {
            dbHelper = DatabaseHelper(this)
            debugDatabaseContent() // Llamar al diagnóstico
            setupRecyclerView()
            setupSearch()
        } catch (e: Exception) {
            Log.e("AlbumesActivity", "Error: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun debugDatabaseContent() {
        Log.d("DEBUG_ALBUMES", "=== DIAGNÓSTICO BASE DE DATOS ===")

        try {
            // Verificar todas las tablas
            val artistas = dbHelper.getAllArtistas()
            val albumes = dbHelper.getAllAlbumes()
            val canciones = dbHelper.getAllCanciones()

            Log.d("DEBUG_ALBUMES", "Artistas en BD: ${artistas.size}")
            Log.d("DEBUG_ALBUMES", "Álbumes en BD: ${albumes.size}")
            Log.d("DEBUG_ALBUMES", "Canciones en BD: ${canciones.size}")

            // Mostrar detalles de artistas
            artistas.forEachIndexed { index, artista ->
                Log.d("DEBUG_ALBUMES", "Artista $index: ${artista.nombre} - ${artista.genero}")
            }

            // Mostrar detalles de álbumes
            albumes.forEachIndexed { index, album ->
                Log.d("DEBUG_ALBUMES", "Álbum $index: ${album.titulo} - Artista: ${album.nombreArtista}")
            }

            // Mostrar detalles de canciones
            canciones.forEachIndexed { index, cancion ->
                Log.d("DEBUG_ALBUMES", "Canción $index: ${cancion.titulo} - ÁlbumID: ${cancion.albumId}")
            }

        } catch (e: Exception) {
            Log.e("DEBUG_ALBUMES", "Error en diagnóstico: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun setupRecyclerView() {
        listaAlbumes = dbHelper.getAllAlbumes()
        Log.d("DEBUG_ALBUMES", "Álbumes para RecyclerView: ${listaAlbumes.size}")

        albumAdapter = AlbumAdapter(listaAlbumes)

        binding.recyclerViewAlbumes.apply {
            layoutManager = LinearLayoutManager(this@AlbumesActivity)
            adapter = albumAdapter
        }

        // Forzar actualización
        albumAdapter.notifyDataSetChanged()
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarAlbumes(newText ?: "")
                return true
            }
        })
    }

    private fun filtrarAlbumes(texto: String) {
        val albumesFiltrados = if (texto.isEmpty()) {
            listaAlbumes
        } else {
            listaAlbumes.filter {
                it.titulo.contains(texto, ignoreCase = true)
            }
        }
        Log.d("DEBUG_ALBUMES", "Filtrando álbumes: ${albumesFiltrados.size} resultados")
        albumAdapter.actualizarLista(albumesFiltrados)
    }
}