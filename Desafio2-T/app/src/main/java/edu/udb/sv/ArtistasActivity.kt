package edu.udb.sv

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.udb.sv.adapters.ArtistaAdapter
import edu.udb.sv.databinding.ActivityArtistasBinding
import edu.udb.sv.db.DatabaseHelper

class ArtistasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistasBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var artistaAdapter: ArtistaAdapter
    private var listaArtistas = emptyList<edu.udb.sv.models.Artista>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("DEBUG_ARTISTAS", "=== INICIANDO ARTISTASACTIVITY ===")

        try {
            dbHelper = DatabaseHelper(this)
            debugDatabaseContent() // Llamar al diagnóstico
            setupRecyclerView()
            setupSearch()
        } catch (e: Exception) {
            Log.e("ArtistasActivity", "Error: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun debugDatabaseContent() {
        Log.d("DEBUG_ARTISTAS", "=== DIAGNÓSTICO BASE DE DATOS ===")

        try {
            val artistas = dbHelper.getAllArtistas()
            Log.d("DEBUG_ARTISTAS", "Artistas en BD: ${artistas.size}")

            artistas.forEachIndexed { index, artista ->
                Log.d("DEBUG_ARTISTAS", "Artista $index: ${artista.nombre} - ${artista.genero}")
            }

        } catch (e: Exception) {
            Log.e("DEBUG_ARTISTAS", "Error en diagnóstico: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun setupRecyclerView() {
        listaArtistas = dbHelper.getAllArtistas()
        Log.d("DEBUG_ARTISTAS", "Artistas para RecyclerView: ${listaArtistas.size}")

        artistaAdapter = ArtistaAdapter(listaArtistas)

        binding.recyclerViewArtistas.apply {
            layoutManager = LinearLayoutManager(this@ArtistasActivity)
            adapter = artistaAdapter
        }

        // Forzar actualización
        artistaAdapter.notifyDataSetChanged()
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarArtistas(newText ?: "")
                return true
            }
        })
    }

    private fun filtrarArtistas(texto: String) {
        val artistasFiltrados = if (texto.isEmpty()) {
            listaArtistas
        } else {
            listaArtistas.filter {
                it.nombre.contains(texto, ignoreCase = true)
            }
        }
        Log.d("DEBUG_ARTISTAS", "Filtrando artistas: ${artistasFiltrados.size} resultados")
        artistaAdapter.actualizarLista(artistasFiltrados)
    }
}