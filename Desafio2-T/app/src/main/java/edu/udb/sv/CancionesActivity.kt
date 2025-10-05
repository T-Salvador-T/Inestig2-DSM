package edu.udb.sv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import edu.udb.sv.databinding.ActivityCancionesBinding
import edu.udb.sv.adapters.CancionAdapter
import edu.udb.sv.db.DatabaseHelper
import edu.udb.sv.models.Cancion

class CancionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCancionesBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var cancionesAdapter: CancionAdapter
    private var listaCanciones: List<Cancion> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCancionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        setupRecyclerView()
        setupSearch()
    }

    private fun setupRecyclerView() {
        listaCanciones = dbHelper.getAllCanciones()
        cancionesAdapter = CancionAdapter(listaCanciones)

        binding.recyclerViewCanciones.apply {
            layoutManager = LinearLayoutManager(this@CancionesActivity)
            adapter = cancionesAdapter
        }
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarCanciones(newText ?: "")
                return true
            }
        })
    }

    private fun filtrarCanciones(texto: String) {
        val cancionesFiltradas = if (texto.isEmpty()) {
            listaCanciones
        } else {
            listaCanciones.filter {
                it.titulo.contains(texto, ignoreCase = true)
            }
        }
        cancionesAdapter.actualizarLista(cancionesFiltradas)
    }
}