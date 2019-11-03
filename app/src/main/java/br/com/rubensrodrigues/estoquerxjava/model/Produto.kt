package br.com.rubensrodrigues.estoquerxjava.model

data class Produto(
    var id: Int = -1,
    var nome: String?,
    var preco: Float?,
    var quantidade: Int?
)