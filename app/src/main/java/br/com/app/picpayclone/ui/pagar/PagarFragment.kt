package br.com.app.picpayclone.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.app.picpayclone.ComponentesViewModel
import br.com.app.picpayclone.R
import br.com.dio.picpayclone.data.Usuario
import kotlinx.android.synthetic.main.fragment_pagar.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PagarFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val pagarViewModel: PagarViewModel by viewModel()
    private val controlador by lazy {
        findNavController()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes =
            ComponentesViewModel.Componentes(bottomNavigation = true)
        setObserver()
    }

    private fun setRecyclerView(usuarios: List<Usuario>) {
        recyclerViewContatos.adapter = PagarAdapter(usuarios) {
            controlador.navigate(PagarFragmentDirections.actionNavigationPagarToTransacaoFragment(it))
        }
    }

    private fun setObserver() {
        pagarViewModel.contatos.observe(viewLifecycleOwner, Observer {
            setRecyclerView(it)
        })
    }

}