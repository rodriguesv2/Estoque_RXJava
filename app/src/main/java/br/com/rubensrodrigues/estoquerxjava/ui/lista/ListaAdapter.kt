package br.com.rubensrodrigues.estoquerxjava.ui.lista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rubensrodrigues.estoquerxjava.R
import br.com.rubensrodrigues.estoquerxjava.model.Produto
import kotlinx.android.synthetic.main.item_produto.view.*

class ListaAdapter(
    private val context: Context,
    private val clickListener: (produto: Produto)->Unit,
    private val onLongClickListener: (produto: Produto)->Unit
): RecyclerView.Adapter<ListaAdapter.ItemViewHolder>() {

    var produtos: List<Produto> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = produtos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false)

        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(produtos[position])
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(produto: Produto){
            with(itemView){
                itemNome.text = produto.nome
                itemPreco.text = context.getString(R.string.preco_produto, produto.preco)
                itemQuantidade.text = context.getString(R.string.quantidade_produto, produto.quantidade)

                setOnClickListener {
                    clickListener(produto)
                }

                setOnLongClickListener {
                    onLongClickListener(produto)
                    true
                }
            }
        }
    }
}