package br.com.zup.autor.view

import br.com.zup.autor.model.Autor


class DetalhesDoAutorResponse(
    val id: Long?,
    val nome: String,
    val email: String,
    val descricao: String,
    val localidade: String

    ) {

    // construtor segundario

    constructor(autor: Autor) : this(autor.id ,autor.nome, autor.email, autor.descricao, autor.endereco.localidade)

}

