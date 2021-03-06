package br.com.app.picpayclone

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.app.picpayclone.data.transacao.TransacaoDAO
import br.com.app.picpayclone.data.transacao.TransacaoLocal

@Database(version = 1, entities = [TransacaoLocal::class], exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun transacaoDAO(): TransacaoDAO

    companion object {
        private const val NOME_BD = "picpayclone.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, NOME_BD)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}