package br.com.app.picpayclone.data

import br.com.dio.picpayclone.data.Usuario

object UsuarioLogado {

    lateinit var usuario: Usuario

    fun isUsuarioLogado() = this::usuario.isInitialized

    fun isUsuarioNaoLogado() = !isUsuarioLogado()

}