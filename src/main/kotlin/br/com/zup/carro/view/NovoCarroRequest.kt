package br.com.zup.carro.view

import br.com.zup.carro.model.Carro
import br.com.zup.compartilhado.validacao.PlacaValid
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

// padrao placa mercosul AAA-9A88

@Introspected
data class NovoCarroRequest(
    @field:NotBlank val modelo: String,
    @field:NotBlank @field:PlacaValid val placa: String
) {
    fun toModel(): Carro {
        return Carro(modelo, placa)
    }
}

