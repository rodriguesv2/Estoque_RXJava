package br.com.rubensrodrigues.estoquerxjava.model

import java.io.Serializable

class Produto : Serializable {

    var id: Int = -1
    var nome: String? = ""
    var preco: Float? = 0f
    var quantidade: Int? = 0
}