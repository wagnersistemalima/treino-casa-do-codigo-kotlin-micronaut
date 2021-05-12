package br.com.zup

import br.com.zup.autor.model.Autor
import br.com.zup.autor.model.Endereco
import br.com.zup.autor.repository.AutorRepository
import br.com.zup.autor.view.EnderecoResponse
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class CadastraAutorControllerTest {

    lateinit var autor: Autor

    // injetar um repository

    @field:Inject
    lateinit var repository: AutorRepository

    // rodar antes de cada teste
    @BeforeEach
    internal fun setUp() {

        // criando cenario
        val enderecoResponse: EnderecoResponse = EnderecoResponse(
            logradouro = "Tenente Adelino Barbosa de Melo",
            localidade = "Campina Grande",
            uf = "PB"
        )

        val endereco: Endereco = Endereco(
            enderecoResponse = enderecoResponse,
            numero = "162", cep = "58410505"
        )

        autor = Autor(
            nome = "Test",
            email = "test@gmail.com",
            descricao = "Um autor de teste",
            endereco = endereco
        )

        // ação aqui

        repository.save(autor)

    }

    // rodar depois de cada teste

    @AfterEach
    internal fun tearDown() {
        repository.deleteAll()
    }

    // 1 cenario de teste

    @Test
    fun deveInserirUmNovoAutor() {

        // assertivas aqui
        Assertions.assertEquals(1, repository.count())

    }
}