package br.com.app.picpayclone.ui.login

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
import br.com.app.picpayclone.data.State
import br.com.app.picpayclone.extension.desaparecer
import br.com.app.picpayclone.extension.getString
import br.com.app.picpayclone.extension.mostrar
import br.com.dio.picpayclone.data.Login
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = ComponentesViewModel.Componentes(bottomNavigation = false)
        setListener()
        setObserve()
    }

    private fun setListener() {
        button.setOnClickListener {
            val usuario = textInputLayoutUsuario.getString()
            val senha = textInputLayoutSenha.getString()
            val login = Login(usuario, senha)
            loginViewModel.login(login)
            //UsuarioLogado.usuario = Usuario("joao")
            //vaiParaHome()
        }
    }

    private fun setObserve() {
        loginViewModel.usuarioState.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is State.Loading -> {
                    progressBar.mostrar()
                }
                is State.Success -> {
                    progressBar.desaparecer()
                    vaiParaHome()
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), "Falha ao autenticar", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun vaiParaHome() {
        val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
        val controlador = findNavController()
        controlador.navigate(direcao)
    }
}