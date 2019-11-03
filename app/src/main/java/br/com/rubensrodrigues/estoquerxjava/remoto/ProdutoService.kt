package br.com.rubensrodrigues.estoquerxjava.remoto

import io.reactivex.Single
import retrofit2.http.*

interface ProdutoService {

    @GET("produto")
    fun getProdutos(): Single<List<Produto>>

    @POST("produto")
    fun sendProduto(): Single<Produto>

    @PUT("produto/{id}")
    fun updateProduto(@Path("id") id: Int): Single<Produto>

    @DELETE("produto/{id}")
    fun deleteProduto(@Path("id") id: Int): Single<Produto>
}