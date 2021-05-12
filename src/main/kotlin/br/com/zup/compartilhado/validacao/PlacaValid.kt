package br.com.zup.compartilhado.validacao

import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Target(FIELD, CONSTRUCTOR)  // pode ser usada, atributos e construtores
@Retention(RUNTIME)                        // vai ser usada em tempo de execução
@MustBeDocumented                  // esta validação vai aparecer na documentação da classe que utiliza-la
@Constraint(validatedBy = [PlacaValidator::class])
annotation class PlacaValid(
    val message: String = "Placa com formato invalido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)


