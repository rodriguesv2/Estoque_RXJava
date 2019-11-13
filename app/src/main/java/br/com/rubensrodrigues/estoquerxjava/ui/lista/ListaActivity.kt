package br.com.rubensrodrigues.estoquerxjava.ui.lista

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
    private val presenter by lazy {
        ListaPresenter().apply {
            attachView(this@ListaActivity)
        }
    }

    private val adapter by lazy {
        val adapter = ListaAdapter(this,
            {
                startActivity(intentParaFormulario(it))
            }, {
                toast(it.preco.toString())
            })
        listaProdutos.adapter = adapter
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

//        val adapter = ListaAdapter(this)
//        adapter.produtos = listOf(
//            Produto(nome ="Playstation 4", preco = 1200f, quantidade = 10),
//            Produto(nome ="iPhone X", preco = 4000f, quantidade = 10),
//            Produto(nome ="Xbox One", preco = 1100f, quantidade = 12),
//            Produto(nome ="Asus Zenfone Max Pro M1", preco = 800f, quantidade = 50),
//            Produto(nome ="TV Samsung 42", preco = 1500f, quantidade = 13),
//            Produto(nome ="Laptop DELL i7", preco = 2400f, quantidade = 5),
//            Produto(nome ="Playstation 4 Pro", preco = 2200f, quantidade = 10),
//            Produto(nome ="Pirulito", preco = 0.25f, quantidade = 10)
//        )
//        listaProdutos.adapter = adapter

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

    override fun mostrarErro(mensagem: String) {
        toast(getString(R.string.erro_api, mensagem))
    }

    override fun mostrarLista(produtos: List<Produto>) {
        adapter.produtos = produtos
    }
}