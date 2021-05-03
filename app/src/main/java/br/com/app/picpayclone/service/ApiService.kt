package br.com.app.picpayclone.service

import br.com.app.picpayclone.data.Token
import br.com.dio.picpayclone.data.Login
import br.com.dio.picpayclone.data.Usuario
import br.com.dio.picpayclone.data.transacao.Transacao
import br.com.dio.picpayclone.data.transacao.TransacaoPage
import retrofit2.http.*

interface ApiService {

    @POST("/autenticacao")
    suspend fun autenticar(@Body login: Login): Token

    @GET("/usuarios/{login}")
    suspend fun getUsuario(@Path("login") login: String): Usuario

    @GET("/usuarios/contatos")
    suspend fun getContatos(@Query("login") login: String): List<Usuario>

    @GET("/usuarios/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String): Usuario

    @POST("/transacoes")
    suspend fun transacao(@Body transacao: Transacao): Transacao

    @GET("/transacoes")
    suspend fun getTransacoes(@Query("login") login: String): TransacaoPage

}