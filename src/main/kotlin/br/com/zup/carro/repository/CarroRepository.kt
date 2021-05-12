package br.com.zup.carro.repository

import br.com.zup.carro.model.Carro
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CarroRepository: JpaRepository<Carro, Long> {
}