package br.com.dio.picpayclone.data.transacao

import br.com.app.picpayclone.data.transacao.TransacaoNetwork
import br.com.dio.picpayclone.data.Pageable
import br.com.dio.picpayclone.data.Sort

data class TransacaoPage(
    val content: List<TransacaoNetwork>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: Sort,
    val totalElements: Int,
    val totalPages: Int
)