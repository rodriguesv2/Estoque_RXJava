package br.com.rubensrodrigues.estoquerxjava.ui.formulario

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.rubensrodrigues.estoquerxjava.Constants
import br.com.rubensrodrigues.estoquerxjava.R
import br.com.rubensrodrigues.estoquerxjava.model.Produto
import br.com.rubensrodrigues.estoquerxjava.ui.toast
import kotlinx.android.synthetic.main.activity_formulario.*
import org.jetbrains.anko.intentFor

class FormularioActivity : AppCompatActivity(), FormularioContract.View {

    private val presenter by lazy {
        var produto: Produto? = null
        if (intent.hasExtra(Constants.PRODUTO_EDICAO)) {
            produto = intent.getSerializableExtra(Constants.PRODUTO_EDICAO) as Produto
        }
        FormularioPresenter(produto).apply { attachView(this@FormularioActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        presenter.preparaEdicao()
        botaoCliqueListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun botaoCliqueListener() {
        formularioBotao.setOnClickListener {
            presenter.aoClicar(
                formularioNome.text.toString(),
                formularioPreco.text.toString().toFloat(),
                formularioQuantidade.text.toString().toInt()
            )
        }
    }

    override fun mostrarProdutoEdicao(produto: Produto) {
        formularioNome.setText(produto.nome)
        formularioPreco.setText(produto.preco.toString())
        formularioQuantidade.setText(produto.quantidade.toString())
    }

    override fun voltarALista() {
        finish()
    }

    override fun mostrarErro(mensagem: String) {
        toast(getString(R.string.erro_api, mensagem))
    }
}

fun Context.intentParaFormulario(produto: Produto? = null): Intent{
    return if (produto != null)
        intentFor<FormularioActivity>(Constants.PRODUTO_EDICAO to produto)
    else
        intentFor<FormularioActivity>()
}
