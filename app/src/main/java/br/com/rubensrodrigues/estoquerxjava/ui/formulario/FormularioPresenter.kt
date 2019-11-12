package br.com.rubensrodrigues.estoquerxjava.ui.formulario

import br.com.rubensrodrigues.estoquerxjava.model.ProdutoRequest
import br.com.rubensrodrigues.estoquerxjava.remoto.RetrofitConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FormularioPresenter: FormularioContract.Presenter {

    private var view: FormularioContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun aoClicar(nome: String, preco: Float, quantidade: Int) {
        val produtoRequest = ProdutoRequest(nome, preco, quantidade)
        compositeDisposable.add(
            RetrofitConfig().getProdutoService().sendProduto(produtoRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {view?.voltarALista()},
                    {view?.mostrarErro(it.message!!)})
        )
    }

    override fun attachView(mvpView: FormularioContract.View?) {
        view = mvpView
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }

}