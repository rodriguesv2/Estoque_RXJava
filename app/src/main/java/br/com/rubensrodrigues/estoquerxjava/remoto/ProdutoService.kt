package br.com.rubensrodrigues.estoquerxjava.remoto

import br.com.rubensrodrigues.estoquerxjava.model.Produto
import br.com.rubensrodrigues.estoquerxjava.model.ProdutoRequest
import io.reactivex.Single
import retrofit2.http.*

interface ProdutoService {

    @GET("produto")
    fun getProdutos(): Single<List<Produto>>

    @POST("produto")
    fun sendProduto(@Body produto: ProdutoRequest): Single<Produto>

    @PUT("produto/{id}")
    fun updateProduto(@Path("id") id: Int, @Body produto: ProdutoRequest): Single<Produto>

    @DELETE("produto/{id}")
    fun deleteProduto(@Path("id") id: Int): Single<Unit>
}