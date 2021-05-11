package br.com.zup.autor.model

import br.com.zup.autor.view.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco (
    enderecoResponse: EnderecoResponse,
    val numero: String,
    val cep: String

) {

    val logradouro: String = enderecoResponse.logradouro
    val localidade: String = enderecoResponse.localidade
    val uf: String = enderecoResponse.uf

}