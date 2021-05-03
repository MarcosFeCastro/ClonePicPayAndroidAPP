package br.com.app.picpayclone.data

import br.com.dio.picpayclone.data.Usuario

object UsuarioLogado {

    lateinit var token: Token

    lateinit var usuario: Usuario

    fun isUsuarioLogado() = this::token.isInitialized

    fun isUsuarioNaoLogado() = !isUsuarioLogado()

}