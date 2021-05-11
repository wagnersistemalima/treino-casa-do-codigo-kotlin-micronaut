package br.com.zup.autor.controller

import br.com.zup.autor.model.Autor
import br.com.zup.autor.repository.AutorRepository
import br.com.zup.autor.view.AtualizaAutorRequest
import br.com.zup.autor.view.DetalhesDoAutorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.util.*
import javax.validation.Valid

@Validated
@Controller(value = "/autores/{id}")
class AtualizaAutorController(val repository: AutorRepository) {

    private val logger = LoggerFactory.getLogger(AtualizaAutorController::class.java)

    // end point atualizar dados autor

    @Put
    fun update(@PathVariable id: Long, @Body @Valid request: AtualizaAutorRequest): HttpResponse<Any> {
        logger.info(".........Iniciando atualização do autor..............")
        val possivelAutor: Optional<Autor> = repository.findById(id)

        if(possivelAutor.isEmpty) {
            logger.info("......Autor não encontrado para atualização.........")
            return HttpResponse.notFound()
        }

        val autor: Autor = possivelAutor.get()
        autor.descricao = request.descricao
        repository.update(autor)

        logger.info(".............Descricao do Autor atualizada com sucesso!............")
        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}