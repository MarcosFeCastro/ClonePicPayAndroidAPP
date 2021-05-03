package br.com.app.picpayclone.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.picpayclone.data.State
import br.com.app.picpayclone.data.Token
import br.com.app.picpayclone.data.UsuarioLogado
import br.com.app.picpayclone.service.ApiService
import br.com.dio.picpayclone.data.Login
import br.com.dio.picpayclone.data.Usuario
import kotlinx.coroutines.launch

class LoginViewModel(private val apiService: ApiService) : ViewModel() {

    val usuarioState = MutableLiveData<State<Usuario>>()
    /*private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario> = _usuario
    val onLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<Exception>()*/

    fun login(login: Login) {
        viewModelScope.launch {
            usuarioState.value = State.Loading()
            try {
                val token = apiService.autenticar(login)
                UsuarioLogado.token = token
                val usuario = apiService.getUsuario(login.usuario)
                usuarioState.value = State.Success(usuario)
                UsuarioLogado.usuario = usuario
            } catch (e: Exception) {
                usuarioState.value = State.Error(e)
            }
        }
    }

}