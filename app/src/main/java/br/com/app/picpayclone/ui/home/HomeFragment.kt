package br.com.app.picpayclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.app.picpayclone.ComponentesViewModel
import br.com.app.picpayclone.R
import br.com.app.picpayclone.data.UsuarioLogado
import br.com.app.picpayclone.extension.formatarMoeda
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = ComponentesViewModel.Componentes(bottomNavigation = true)
        if (UsuarioLogado.isUsuarioNaoLogado()) {
            vaiParaLogin()
            return
        }
        setObserves()
    }

    private fun setObserves() {
        homeViewModel.saldo.observe(viewLifecycleOwner, Observer {
            it?.let { saldo ->
                textViewSaldo.text = saldo.formatarMoeda()
            }
        })
        homeViewModel.transacoes.observe(viewLifecycleOwner, Observer {
            it?.let { transacoes ->
                recyclerView.adapter = HomeAdapter(transacoes)
            }
        })
        homeViewModel.onLoading.observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBarSaldo.visibility = VISIBLE
                progressBarTransferencia.visibility = VISIBLE
            } else {
                progressBarSaldo.visibility = GONE
                progressBarTransferencia.visibility = GONE
            }
        })
        homeViewModel.onError.observe(viewLifecycleOwner, Observer {
            it?.let { error ->
                Toast.makeText(this@HomeFragment.context, error, Toast.LENGTH_SHORT)
            }
        })
    }

    private fun vaiParaLogin() {
        controlador.navigate(HomeFragmentDirections.actionGlobalLoginFragment())
    }

}