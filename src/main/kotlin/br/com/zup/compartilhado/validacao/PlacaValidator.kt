package br.com.zup.compartilhado.validacao

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton

@Singleton
class PlacaValidator: ConstraintValidator<PlacaValid, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<PlacaValid>,
        context: ConstraintValidatorContext
    ): Boolean {
       // logica aqui    placa = AAA-8P88 / regex [A-Z]{3}[0-9][0-9A-Z][0-9]{2}

        if (value == null) {  // campo opcional, se não vinher preenchido nao tem o que validar
            return true
        }

        return value.matches("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}".toRegex())
    }

}