package br.com.app.picpayclone.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.picpayclone.data.UsuarioLogado
import br.com.app.picpayclone.repository.TransacaoRepository
import br.com.app.picpayclone.service.ApiService
import br.com.dio.picpayclone.data.transacao.Transacao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val transacaoRepository: TransacaoRepository ) : ViewModel() {

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double> = _saldo
    private val _transacoes = MutableLiveData<List<Transacao>>()
    val transacoes: LiveData<List<Transacao>> = _transacoes
    val onError = MutableLiveData<String>()
    val onLoading = MutableLiveData<Boolean>()

    init {
        if (UsuarioLogado.isUsuarioLogado()) {
            viewModelScope.launch(Dispatchers.IO) {
                onLoading.postValue(true)
                try {
                    val login = UsuarioLogado.usuario.login
                    val saldo = transacaoRepository.getSaldo(login)
                    _saldo.postValue(saldo)
                    val historico = transacaoRepository.getTransacoes(login)
                    _transacoes.postValue(historico)
                } catch (e: Exception) {
                    onError.postValue(e.message)
                }
                onLoading.postValue(false)
            }
        }
    }

}