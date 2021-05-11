package br.com.zup.autor.view

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class AtualizaAutorRequest(
    @field: NotBlank val descricao: String)
