package br.com.app.picpayclone.ui.transacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.app.picpayclone.ComponentesViewModel
import br.com.app.picpayclone.R
import br.com.app.picpayclone.data.UsuarioLogado
import br.com.app.picpayclone.extension.formatar
import br.com.dio.picpayclone.data.CartaoCredito
import br.com.dio.picpayclone.data.transacao.Transacao
import kotlinx.android.synthetic.main.fragment_transacao.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class TransacaoFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val args by navArgs<TransacaoFragmentArgs>()
    private val usuario by lazy { args.usuario }
    private val transacaoViewModel: TransacaoViewModel by viewModel()
    private val controlador by lazy {
        findNavController()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transacao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes =
            ComponentesViewModel.Componentes(bottomNavigation = true)
        setUsuarios()
        setRadioGroup()
        setListener()
        transacaoViewModel.transacao.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val direcao = TransacaoFragmentDirections.actionTransacaoFragmentToNavigationPagar()
            controlador.navigate(direcao)
        })
    }

    private fun setListener() {
        buttonTransferir.setOnClickListener {
            val isCartaoCredito = radioButtonCartaoCredito.isChecked
            val valor = getValor()
            val transacao = if (isCartaoCredito) {
                criarTransferenciaCartao(isCartaoCredito, valor)
            } else {
                criarTransferenciaSaldo(valor)
            }
            transacaoViewModel.tranferir(transacao)
        }
    }

    private fun criarTransferenciaCartao(cartaoCredito: Boolean, valor: Double) : Transacao {
        return Transacao(
                Transacao.gerarHash(),
                UsuarioLogado.usuario,
                usuario,
                Calendar.getInstance().formatar(),
                cartaoCredito,
                valor,
                criarCartaoCredito()
        )
    }

    private fun criarTransferenciaSaldo(valor: Double) : Transacao {
        return Transacao(
                Transacao.gerarHash(),
                UsuarioLogado.usuario,
                usuario,
                Calendar.getInstance().formatar(),
                false,
                valor,
                criarCartaoCredito()
        )
    }

    private fun criarCartaoCredito(): CartaoCredito {
        val numero = editTextNumero.text.toString()
        val nome = editTextNome.text.toString()
        val vencimento = editTextVencimento.text.toString()
        val cvc = editTextCVC.text.toString()
        return CartaoCredito(
                numeroCartao = numero,
                nomeTitular = nome,
                dataExpiracao = vencimento,
                codigoSeguranca = cvc,
                usuario = UsuarioLogado.usuario
        )
    }

    private fun getValor() : Double {
        val valor = editTextValor.text.toString()
        return if (valor.isEmpty()) {
            0.0
        } else {
            valor.toDouble()
        }
    }

    private fun setUsuarios() {
        text_view_login.text = usuario.login
        text_view_nome_completo.text = usuario.nomeCompleto
    }

    private fun setRadioGroup() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioButtonCartaoCredito -> {
                    constraintLayoutCartao.visibility = VISIBLE
                }
                R.id.radioButtonSaldo -> {
                    constraintLayoutCartao.visibility = GONE
                }
            }
        }
    }

}