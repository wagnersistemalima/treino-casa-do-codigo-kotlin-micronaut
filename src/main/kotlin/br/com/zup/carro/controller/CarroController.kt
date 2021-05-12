package br.com.zup.carro.controller

import br.com.zup.carro.model.Carro
import br.com.zup.carro.repository.CarroRepository
import br.com.zup.carro.view.NovoCarroRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Validated
@Controller(value = "/carros")
open class CarroController(val repository: CarroRepository) {

    private val logger = LoggerFactory.getLogger(CarroController::class.java)

    // end point insert

    @Post
    open fun insert(@Body @Valid request: NovoCarroRequest): String {
        logger.info("..........Iniciando cadastro do carro............")
        val carro: Carro = request.toModel()

        logger.info("........Carro cadastrado com sucesso.......")
        return carro.toString()
    }
}