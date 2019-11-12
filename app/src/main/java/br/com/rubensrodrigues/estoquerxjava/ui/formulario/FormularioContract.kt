package br.com.rubensrodrigues.estoquerxjava.ui.formulario

interface FormularioContract {

    interface View{
        fun voltarALista()
        fun mostrarErro(mensagem: String)
    }

    interface Presenter{
        fun aoClicar(nome: String, preco: Float, quantidade: Int)
        fun attachView(mvpView: View?)
        fun detachView()
    }
}