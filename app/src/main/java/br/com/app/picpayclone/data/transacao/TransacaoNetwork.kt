package br.com.app.picpayclone.data.transacao

import br.com.dio.picpayclone.data.CartaoCredito
import br.com.dio.picpayclone.data.Usuario

data class TransacaoNetwork(
    val codigo: String? = "",
    val origem: Usuario? = Usuario(),
    val destino: Usuario? = Usuario(),
    val dataHora: String? = "",
    val isCartaoCredito: Boolean? = false,
    val valor: Double? = 0.0,
    val cartaoCredito: CartaoCredito? = CartaoCredito()
)
