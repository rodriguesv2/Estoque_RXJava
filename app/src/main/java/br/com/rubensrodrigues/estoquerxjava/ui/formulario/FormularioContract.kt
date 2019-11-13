package br.com.rubensrodrigues.estoquerxjava.ui.formulario

import br.com.rubensrodrigues.estoquerxjava.model.Produto

interface FormularioContract {

    interface View{
        fun voltarALista()
        fun mostrarProdutoEdicao(produto: Produto)
        fun mostrarErro(mensagem: String)
    }

    interface Presenter{
        fun aoClicar(nome: String, preco: Float, quantidade: Int)
        fun preparaEdicao()
        fun attachView(mvpView: View?)
        fun detachView()
    }
}