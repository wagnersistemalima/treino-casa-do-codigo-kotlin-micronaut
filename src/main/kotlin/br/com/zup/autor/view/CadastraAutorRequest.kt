package br.com.zup.autor.view

import br.com.zup.autor.model.Autor
import br.com.zup.autor.model.Endereco
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class CadastraAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val numero: String,
    @field:NotBlank val cep: String


    ) {

    fun toModel(enderecoResponse: EnderecoResponse): Autor {
        val endereco = Endereco(enderecoResponse, numero, cep)
        return Autor(nome, email, descricao,endereco)
    }



}

