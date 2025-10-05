package edu.udb.sv.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.udb.sv.models.Album
import edu.udb.sv.models.Artista
import edu.udb.sv.models.Cancion

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "catalogo_musica.db"
        private const val DATABASE_VERSION = 1

        // Tablas
        const val TABLE_ARTISTAS = "artistas"
        const val TABLE_ALBUMES = "albumes"
        const val TABLE_CANCIONES = "canciones"

        // Columnas
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_GENERO = "genero"
        const val COLUMN_TITULO = "titulo"
        const val COLUMN_DESCRIPCION = "descripcion"
        const val COLUMN_IMAGEN = "imagen"
        const val COLUMN_ARTISTA_ID = "artista_id"
        const val COLUMN_ANO = "año"
        const val COLUMN_PORTADA = "portada"
        const val COLUMN_ALBUM_ID = "album_id"
        const val COLUMN_DURACION = "duracion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tablas
        val createArtistasTable = """
            CREATE TABLE $TABLE_ARTISTAS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_GENERO TEXT NOT NULL,
                $COLUMN_DESCRIPCION TEXT,
                $COLUMN_IMAGEN TEXT
            )
        """.trimIndent()

        val createAlbumesTable = """
            CREATE TABLE $TABLE_ALBUMES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT NOT NULL,
                $COLUMN_ARTISTA_ID INTEGER NOT NULL,
                $COLUMN_ANO INTEGER,
                $COLUMN_GENERO TEXT,
                $COLUMN_PORTADA TEXT,
                FOREIGN KEY ($COLUMN_ARTISTA_ID) REFERENCES $TABLE_ARTISTAS($COLUMN_ID)
            )
        """.trimIndent()

        val createCancionesTable = """
            CREATE TABLE $TABLE_CANCIONES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITULO TEXT NOT NULL,
                $COLUMN_ARTISTA_ID INTEGER NOT NULL,
                $COLUMN_ALBUM_ID INTEGER NOT NULL,
                $COLUMN_DURACION TEXT,
                $COLUMN_GENERO TEXT,
                FOREIGN KEY ($COLUMN_ARTISTA_ID) REFERENCES $TABLE_ARTISTAS($COLUMN_ID),
                FOREIGN KEY ($COLUMN_ALBUM_ID) REFERENCES $TABLE_ALBUMES($COLUMN_ID)
            )
        """.trimIndent()

        db.execSQL(createArtistasTable)
        db.execSQL(createAlbumesTable)
        db.execSQL(createCancionesTable)

        // Insertar datos de prueba
        insertDatosPrueba(db)
    }

    private fun insertDatosPrueba(db: SQLiteDatabase) {
        Log.d("DatabaseHelper", "Insertando datos de prueba...")

        // Insertar artistas
        val artistas = listOf(
            ContentValues().apply {
                put(COLUMN_NOMBRE, "Bad Bunny")
                put(COLUMN_GENERO, "Reggaeton")
                put(COLUMN_DESCRIPCION, "Artista puertorriqueño de reggaeton y trap latino")
            },
            ContentValues().apply {
                put(COLUMN_NOMBRE, "Taylor Swift")
                put(COLUMN_GENERO, "Pop")
                put(COLUMN_DESCRIPCION, "Cantante y compositora country/pop estadounidense")
            },
            ContentValues().apply {
                put(COLUMN_NOMBRE, "Metallica")
                put(COLUMN_GENERO, "Heavy Metal")
                put(COLUMN_DESCRIPCION, "Banda estadounidense de heavy metal")
            },
            ContentValues().apply {
                put(COLUMN_NOMBRE, "Shakira")
                put(COLUMN_GENERO, "Pop Latino")
                put(COLUMN_DESCRIPCION, "Cantante, compositora y bailarina colombiana")
            }
        )

        artistas.forEach { values ->
            val id = db.insert(TABLE_ARTISTAS, null, values)
            Log.d("DatabaseHelper", "Artista insertado ID: $id")
        }

        // Insertar álbumes
        val albumes = listOf(
            ContentValues().apply {
                put(COLUMN_TITULO, "Un Verano Sin Ti")
                put(COLUMN_ARTISTA_ID, 1)
                put(COLUMN_ANO, 2022)
                put(COLUMN_GENERO, "Reggaeton")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "1989")
                put(COLUMN_ARTISTA_ID, 2)
                put(COLUMN_ANO, 2014)
                put(COLUMN_GENERO, "Pop")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Master of Puppets")
                put(COLUMN_ARTISTA_ID, 3)
                put(COLUMN_ANO, 1986)
                put(COLUMN_GENERO, "Heavy Metal")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Fijación Oral Vol. 1")
                put(COLUMN_ARTISTA_ID, 4)
                put(COLUMN_ANO, 2005)
                put(COLUMN_GENERO, "Pop Latino")
            }
        )

        albumes.forEach { values ->
            val id = db.insert(TABLE_ALBUMES, null, values)
            Log.d("DatabaseHelper", "Álbum insertado ID: $id")
        }

        // Insertar canciones
        val canciones = listOf(
            ContentValues().apply {
                put(COLUMN_TITULO, "Tití Me Preguntó")
                put(COLUMN_ARTISTA_ID, 1)
                put(COLUMN_ALBUM_ID, 1)
                put(COLUMN_DURACION, "4:03")
                put(COLUMN_GENERO, "Reggaeton")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Moscow Mule")
                put(COLUMN_ARTISTA_ID, 1)
                put(COLUMN_ALBUM_ID, 1)
                put(COLUMN_DURACION, "4:05")
                put(COLUMN_GENERO, "Reggaeton")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Shake It Off")
                put(COLUMN_ARTISTA_ID, 2)
                put(COLUMN_ALBUM_ID, 2)
                put(COLUMN_DURACION, "3:39")
                put(COLUMN_GENERO, "Pop")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Blank Space")
                put(COLUMN_ARTISTA_ID, 2)
                put(COLUMN_ALBUM_ID, 2)
                put(COLUMN_DURACION, "3:51")
                put(COLUMN_GENERO, "Pop")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Master of Puppets")
                put(COLUMN_ARTISTA_ID, 3)
                put(COLUMN_ALBUM_ID, 3)
                put(COLUMN_DURACION, "8:35")
                put(COLUMN_GENERO, "Heavy Metal")
            },
            ContentValues().apply {
                put(COLUMN_TITULO, "Battery")
                put(COLUMN_ARTISTA_ID, 3)
                put(COLUMN_ALBUM_ID, 3)
                put(COLUMN_DURACION, "5:12")
                put(COLUMN_GENERO, "Heavy Metal")
            }
        )

        canciones.forEach { values ->
            val id = db.insert(TABLE_CANCIONES, null, values)
            Log.d("DatabaseHelper", "Canción insertada ID: $id")
        }

        Log.d("DatabaseHelper", "Datos de prueba insertados correctamente")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CANCIONES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ALBUMES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ARTISTAS")
        onCreate(db)
    }

    // MÉTODOS PARA OBTENER DATOS - VERIFICAR QUE ESTÉN CORRECTOS
// MÉTODOS PARA OBTENER DATOS - CORREGIDOS
    fun getAllArtistas(): List<Artista> {
        val artistas = mutableListOf<Artista>()
        val db = readableDatabase
        val cursor = db.query(TABLE_ARTISTAS, null, null, null, null, null, COLUMN_NOMBRE)

        Log.d("DatabaseHelper", "Consultando artistas...")

        while (cursor.moveToNext()) {
            val artista = Artista(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                genero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO)),
                descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION))
                    ?: "", // Manejar NULL
                imagen = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN))
                    ?: "" // Manejar NULL
            )
            artistas.add(artista)
            Log.d("DatabaseHelper", "Artista encontrado: ${artista.nombre}")
        }

        Log.d("DatabaseHelper", "Total artistas encontrados: ${artistas.size}")
        cursor.close()
        return artistas
    }

    fun getAllAlbumes(): List<Album> {
        val albumes = mutableListOf<Album>()
        val db = readableDatabase
        val cursor = db.query(TABLE_ALBUMES, null, null, null, null, null, COLUMN_TITULO)

        Log.d("DatabaseHelper", "Consultando álbumes...")

        while (cursor.moveToNext()) {
            val album = Album(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO)),
                artistaId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARTISTA_ID)),
                año = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ANO)),
                genero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO))
                    ?: "", // Manejar NULL
                portada = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PORTADA))
                    ?: "" // Manejar NULL
            )
            albumes.add(album)
            Log.d("DatabaseHelper", "Álbum encontrado: ${album.titulo}")
        }

        Log.d("DatabaseHelper", "Total álbumes encontrados: ${albumes.size}")
        cursor.close()
        return albumes
    }

    fun getAllCanciones(): List<Cancion> {
        val canciones = mutableListOf<Cancion>()
        val db = readableDatabase
        val cursor = db.query(TABLE_CANCIONES, null, null, null, null, null, COLUMN_TITULO)

        Log.d("DatabaseHelper", "Consultando canciones...")

        while (cursor.moveToNext()) {
            val cancion = Cancion(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO)),
                artistaId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ARTISTA_ID)),
                albumId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ALBUM_ID)),
                duracion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DURACION))
                    ?: "", // Manejar NULL
                genero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO))
                    ?: "" // Manejar NULL
            )
            canciones.add(cancion)
            Log.d("DatabaseHelper", "Canción encontrada: ${cancion.titulo}")
        }

        Log.d("DatabaseHelper", "Total canciones encontradas: ${canciones.size}")
        cursor.close()
        return canciones
    }
}