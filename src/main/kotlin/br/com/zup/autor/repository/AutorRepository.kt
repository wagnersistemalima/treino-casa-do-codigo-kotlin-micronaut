package br.com.zup.autor.repository

import br.com.zup.autor.model.Autor
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository: JpaRepository<Autor, Long> {

    fun findByEmail(email: String): Optional<Autor>

}