package br.com.rubensrodrigues.estoquerxjava.ui.lista

import br.com.rubensrodrigues.estoquerxjava.model.Produto

class ListaContract {

    interface View{
        fun mostrarLista(produtos: List<Produto>)
        fun mostrarErro(mensagem: String)
    }

    interface Presenter{
        fun carregarLista()
        fun attachView(mvpView: View?)
        fun detachView()
    }
}