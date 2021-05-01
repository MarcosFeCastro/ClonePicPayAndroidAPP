package br.com.app.picpayclone.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.app.picpayclone.ComponentesViewModel
import br.com.app.picpayclone.R
import br.com.app.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.data.Usuario
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes =
            ComponentesViewModel.Componentes(bottomNavigation = false)
        setListener()
    }

    private fun setListener() {
        button.setOnClickListener {
            UsuarioLogado.usuario = Usuario("joao")
            vaiParaHome()
        }
    }

    private fun vaiParaHome() {
        val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
        val controlador = findNavController()
        controlador.navigate(direcao)
    }
}