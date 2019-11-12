package br.com.rubensrodrigues.estoquerxjava.model

import java.io.Serializable

class ProdutoRequest(
    var nome: String,
    var preco: Float,
    var quantidade: Int
): Serializable