package br.com.fortestecnologia.infinitySagaMarvel.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmEntity

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmEntity: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(films: List<FilmEntity>)

//    @Update
//    suspend fun update(filmEntity: FilmEntity)

//    @Query("DELETE FROM film WHERE id = :id")
//    suspend fun delete(id: Long)

//    @Query("DELETE FROM film")
//    suspend fun deleteAll()

    @Query("SELECT * FROM film")
    fun getAll() :  LiveData<List<FilmEntity>>

    @Query("SELECT * FROM film WHERE id = :id")
    fun get(id: Int): LiveData<FilmEntity>


}