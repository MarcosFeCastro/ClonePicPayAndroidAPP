package br.com.app.picpayclone.data.transacao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransacaoDAO {

    @Query("SELECT * FROM transacao")
    fun getAll(): LiveData<List<TransacaoLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(transacaoLocal: TransacaoLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transacoes: List<TransacaoLocal>)

    @Update
    fun update(transacaoLocal: TransacaoLocal)

    @Delete
    fun delete(transacaoLocal: TransacaoLocal)

}
