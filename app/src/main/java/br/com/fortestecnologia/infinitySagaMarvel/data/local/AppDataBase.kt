package br.com.fortestecnologia.infinitySagaMarvel.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmEntity
import br.com.fortestecnologia.infinitySagaMarvel.data.entities.FilmList

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){

    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDataBase::class.java, "film")
                .fallbackToDestructiveMigration()
                .build()
    }

}