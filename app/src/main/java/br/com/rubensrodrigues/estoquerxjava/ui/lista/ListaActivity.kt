package br.com.rubensrodrigues.estoquerxjava.ui.lista

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.rubensrodrigues.estoquerxjava.R
import br.com.rubensrodrigues.estoquerxjava.model.Produto
import br.com.rubensrodrigues.estoquerxjava.ui.formulario.intentParaFormulario
import br.com.rubensrodrigues.estoquerxjava.ui.toast
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity(), ListaContract.View {

    private val adapter by lazy {
        val adapter = ListaAdapter(this, {
            startActivity(intentParaFormulario(it))
        }, {
            dialogDeRemocao(it)
        })
        listaProdutos.adapter = adapter
        adapter
    }

    private val presenter by lazy {
        ListaPresenter().apply {
            attachView(this@ListaActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        title = "Produtos"
        
        presenter.carregarLista()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onResume() {
        super.onResume()
        presenter.carregarLista()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.menuItemAdd -> {
                startActivity(intentParaFormulario())
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun dialogDeRemocao(produto: Produto) {
        AlertDialog.Builder(this)
            .setTitle("Remover")
            .setMessage("Deseja remover o produto?")
            .setNegativeButton("Não", null)
            .setPositiveButton("Sim") { dialog, which ->
                presenter.removeProduto(produto)
            }
            .show()
    }

    override fun mostrarErro(mensagem: String) { toast(getString(R.string.erro_api, mensagem)) }
    override fun mostrarLista(produtos: List<Produto>) { adapter.produtos = produtos }
}