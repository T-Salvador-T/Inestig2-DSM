package edu.udb.sv

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.udb.sv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SOLUCIÓN: Cambiar findViewById(R.id.main) por binding.root
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnCanciones.setOnClickListener {
            val intent = Intent(this, CancionesActivity::class.java)
            startActivity(intent)
        }

        binding.btnAlbumes.setOnClickListener {
            val intent = Intent(this, AlbumesActivity::class.java)
            startActivity(intent)
        }

        binding.btnArtistas.setOnClickListener {
            val intent = Intent(this, ArtistasActivity::class.java)
            startActivity(intent)
        }
    }
}