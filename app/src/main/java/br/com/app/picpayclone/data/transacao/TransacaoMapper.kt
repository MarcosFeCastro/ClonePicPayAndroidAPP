package br.com.app.picpayclone.data.transacao

import br.com.dio.picpayclone.data.CartaoCredito
import br.com.dio.picpayclone.data.Usuario
import br.com.dio.picpayclone.data.transacao.Transacao

fun TransacaoNetwork.toModel(): Transacao = Transacao(
    codigo = codigo.orEmpty(),
    origem = origem ?: Usuario(),
    destino = destino ?: Usuario(),
    dataHora = dataHora.orEmpty(),
    isCartaoCredito = isCartaoCredito ?: false,
    valor = valor ?: 0.0,
    cartaoCredito = cartaoCredito ?: CartaoCredito()
)

fun List<TransacaoNetwork>.toModel(): List<Transacao> = this.map { it.toModel() }

fun Transacao.toLocal(): TransacaoLocal = TransacaoLocal(
    codigo = codigo,
    origem = origem.login,
    destino = destino.login,
    dataHora = dataHora,
    isCartaoCredito = isCartaoCredito,
    valor = valor
)

fun List<Transacao>.toLocal() = this.map { it.toLocal() }
