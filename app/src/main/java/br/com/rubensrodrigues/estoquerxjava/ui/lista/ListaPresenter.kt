package br.com.rubensrodrigues.estoquerxjava.ui.lista

import android.util.Log
import br.com.rubensrodrigues.estoquerxjava.remoto.RetrofitConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListaPresenter: ListaContract.Presenter {

    private var view : ListaContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun carregarLista() {
        compositeDisposable.add(
            RetrofitConfig().getProdutoService().getProdutos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { view?.mostrarLista(produtos = it) },
                    {
                        view?.mostrarErro(it.message!!)
                        Log.d("OKHTTP", it.message)
                    }
                )
        )
    }

    override fun attachView(mvpView: ListaContract.View?) {
        view = mvpView
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }
}