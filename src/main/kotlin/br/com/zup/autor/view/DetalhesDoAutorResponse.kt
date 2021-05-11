package br.com.zup.autor.view

import br.com.zup.autor.model.Autor


class DetalhesDoAutorResponse(
    autor: Autor

    ) {

    val nome: String = autor.nome
    val email: String = autor.email
    val descricao: String = autor.descricao

}

