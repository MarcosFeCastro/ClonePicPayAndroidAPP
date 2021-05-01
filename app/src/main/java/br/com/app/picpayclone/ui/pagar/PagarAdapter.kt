package br.com.app.picpayclone.ui.pagar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.app.picpayclone.R
import br.com.dio.picpayclone.data.Usuario
import kotlinx.android.synthetic.main.item_contatos.view.*

class PagarAdapter(
        private val usuarios: List<Usuario>,
        private val onClick: (Usuario) -> Unit
) : RecyclerView.Adapter<PagarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_contatos, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(usuarios[position])

    override fun getItemCount(): Int = usuarios.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(usuario: Usuario) {
            with(itemView) {
                tv_login.text = usuario.login
                tv_nome_completo.text = usuario.nomeCompleto
                setOnClickListener {
                    onClick(usuario)
                }
            }
        }
    }

}