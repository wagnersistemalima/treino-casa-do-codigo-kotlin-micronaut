package br.com.zup.autor.controller

import br.com.zup.apiExterna.EnderecoClient
import br.com.zup.autor.model.Autor
import br.com.zup.autor.repository.AutorRepository
import br.com.zup.autor.view.CadastraAutorRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
open class CadastraAutorController(
    val repository: AutorRepository,
    val enderecoClient: EnderecoClient

    ) {

    private val logger = LoggerFactory.getLogger(CadastraAutorController::class.java)

    // end point / insert autor

    @Transactional
    @Post
    open fun insert(@Body @Valid request: CadastraAutorRequest): HttpResponse<Any> {
        logger.info("........Iniciando cadastro do autor........")

        val enderecoResponse = enderecoClient.consulta(request.cep)



        logger.info("....Consultando cep do autor na api externa.......")

        val autor: Autor = request.toModel(enderecoResponse.body()!!)
        repository.save(autor)

        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))

        logger.info(".........Autor cadastrado com sucesso!...............")
        return HttpResponse.created(uri)
    }

}