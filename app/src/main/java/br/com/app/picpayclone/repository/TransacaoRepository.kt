package br.com.app.picpayclone.repository

import br.com.app.picpayclone.data.transacao.TransacaoDAO
import br.com.app.picpayclone.data.transacao.toLocal
import br.com.app.picpayclone.data.transacao.toModel
import br.com.app.picpayclone.service.ApiService
import br.com.dio.picpayclone.data.transacao.Transacao

interface TransacaoRepository {
    suspend fun getTransacoes(login: String): List<Transacao>
    suspend fun getSaldo(login: String): Double
}

class TransacaoRepositoryImpl(
    private val apiService: ApiService,
    private val transacaoDAO: TransacaoDAO
) : TransacaoRepository {

    override suspend fun getTransacoes(login: String): List<Transacao> {
        val transacoes = apiService.getTransacoes(login).content.toModel()
        transacaoDAO.save(transacoes.toLocal())
        return transacoes
    }

    override suspend fun getSaldo(login: String): Double = apiService.getSaldo(login).saldo

}