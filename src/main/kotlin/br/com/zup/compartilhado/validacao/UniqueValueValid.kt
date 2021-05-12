package br.com.zup.compartilhado.validacao

import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR) // pode ser usada em atributos e construtores
@Retention(AnnotationRetention.RUNTIME) // vai ser usada em tempo de execução
@MustBeDocumented       // esta classe deve ser documentada
@Constraint(validatedBy = [UniqueValueValidator::class])
annotation class UniqueValueValid(
    val message: String = "Validation error, este dado é unico e já existe cadastro no banco",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)
