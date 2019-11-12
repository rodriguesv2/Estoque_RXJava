package br.com.rubensrodrigues.estoquerxjava.ui.formulario

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.rubensrodrigues.estoquerxjava.R
import br.com.rubensrodrigues.estoquerxjava.ui.toast
import kotlinx.android.synthetic.main.activity_formulario.*
import org.jetbrains.anko.intentFor

class FormularioActivity : AppCompatActivity(), FormularioContract.View {

    private val presenter by lazy {
        FormularioPresenter().apply { attachView(this@FormularioActivity) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        formularioBotao.setOnClickListener {
            presenter.aoClicar(
                formularioNome.text.toString(),
                formularioPreco.text.toString().toFloat(),
                formularioQuantidade.text.toString().toInt()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun voltarALista() {
        finish()
    }

    override fun mostrarErro(mensagem: String) {
        toast(getString(R.string.erro_api, mensagem))
    }
}

fun Context.intentParaFormulario() = intentFor<FormularioActivity>()
