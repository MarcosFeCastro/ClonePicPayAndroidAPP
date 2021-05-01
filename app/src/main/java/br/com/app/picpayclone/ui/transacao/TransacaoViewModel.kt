package br.com.app.picpayclone.ui.transacao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.picpayclone.service.ApiService
import br.com.dio.picpayclone.data.transacao.Transacao
import kotlinx.coroutines.launch

class TransacaoViewModel(private val apiService: ApiService) : ViewModel() {

    private val _transacao = MutableLiveData<Transacao>()
    val transacao: LiveData<Transacao> = _transacao

    fun tranferir(transacao: Transacao) {
        viewModelScope.launch {
            try {
                _transacao.value = apiService.transacao(transacao)
            } catch (e: Exception) {
                Log.e("Error", "error")
            }
        }
    }

}