package br.com.zup.carro.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Carro(
    val modelo: String,
    val placa: String
    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    override fun toString(): String {
        return "Carro(modelo='$modelo', placa='$placa', id=$id)"
    }


}