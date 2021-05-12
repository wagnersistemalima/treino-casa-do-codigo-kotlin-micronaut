package br.com.zup.compartilhado.validacao

import br.com.zup.autor.repository.AutorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton

@Singleton
class UniqueValueValidator(val repository: AutorRepository): ConstraintValidator<UniqueValueValid, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<UniqueValueValid>,
        context: ConstraintValidatorContext
    ): Boolean {
        if (value == null) { // campo opcional, se não vinher preenchido nao tem o que validar
            return true
        }
        // vai no banco, se no campo email estiver vazio esta validado
        return repository.findByEmail(value!!).isEmpty
    }
}