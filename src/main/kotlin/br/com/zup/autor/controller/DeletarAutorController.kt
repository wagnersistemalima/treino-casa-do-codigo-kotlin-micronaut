package br.com.zup.autor.controller

import br.com.zup.autor.model.Autor
import br.com.zup.autor.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import org.slf4j.LoggerFactory
import java.util.*

@Controller(value = "/autores/{id}")
class DeletarAutorController (val repository: AutorRepository){

    private val logger = LoggerFactory.getLogger(DeletarAutorController::class.java)

    // end point deletar autor pelo id

    @Delete
    fun delete(@PathVariable id: Long): HttpResponse<Any> {
        logger.info(".........Iniciando deletar um autor pelo id..........")
        val possivelAutor: Optional<Autor> = repository.findById(id)

        if(possivelAutor.isEmpty) {
            logger.warn("......Autor não encontrado para deletar......")
            return HttpResponse.notFound()
        }

        val autor: Autor = possivelAutor.get()
        repository.delete(autor)
        logger.info("......Autor deletado com sucesso.......")
        return HttpResponse.noContent()
    }
}