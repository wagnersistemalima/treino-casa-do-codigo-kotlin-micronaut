package br.com.zup.autor.controller

import br.com.zup.autor.model.Autor
import br.com.zup.autor.repository.AutorRepository
import br.com.zup.autor.view.DetalhesDoAutorResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import org.slf4j.LoggerFactory
import java.util.*


@Controller(value = "/autores")
class BuscarAutoresController(val repository: AutorRepository) {

    private val logger = LoggerFactory.getLogger(BuscarAutoresController::class.java)

    // end point get / pesquisa /autores?email=carlos@gmail.com ou assim: /autores

    @Get
    fun buscarTodos(@QueryValue(defaultValue = "") email: String ): HttpResponse<Any> {
        logger.info("............Iniciando a pesquisa de autores..............")

        if(email.isBlank()) {
            val autores = repository.findAll()
            val lista: List<DetalhesDoAutorResponse> = autores.map { autor -> DetalhesDoAutorResponse(autor) }
            logger.info("..........Retornando a pesquisa todos os autores............")
            return HttpResponse.ok(lista)
        }

        val possivelAutor: Optional<Autor> = repository.findByEmail(email)

        if(possivelAutor.isEmpty) {
            logger.warn("Autor não encontrado")
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        logger.info("..........Retornando o autor pelo email.............")
        return HttpResponse.ok(DetalhesDoAutorResponse(possivelAutor.get()))
    }


}