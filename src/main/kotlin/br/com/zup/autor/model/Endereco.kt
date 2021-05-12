package br.com.zup.autor.model

import br.com.zup.autor.view.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco (
    enderecoResponse: EnderecoResponse,
    var numero: String,
    var cep: String

) {

    var logradouro: String = enderecoResponse.logradouro
    var localidade: String = enderecoResponse.localidade
    var uf: String = enderecoResponse.uf

}