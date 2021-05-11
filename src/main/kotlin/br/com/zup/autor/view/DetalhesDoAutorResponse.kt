package br.com.zup.autor.view

import br.com.zup.autor.model.Autor


class DetalhesDoAutorResponse(
    autor: Autor

    ) {

    val id: Long? = autor.id
    val nome: String = autor.nome
    val email: String = autor.email
    val descricao: String = autor.descricao
    val localidade: String = autor.endereco.localidade


}

