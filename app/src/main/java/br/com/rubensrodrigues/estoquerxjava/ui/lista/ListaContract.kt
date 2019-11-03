package br.com.rubensrodrigues.estoquerxjava.ui.lista

import br.com.rubensrodrigues.estoquerxjava.model.Produto

class ListaContract {

    interface View{
        fun mostrarLista()
    }

    interface Presenter{
        fun carregarLista(produtos: List<Produto>)
    }
}