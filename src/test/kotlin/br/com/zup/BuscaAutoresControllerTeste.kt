package br.com.zup

import br.com.zup.autor.controller.BuscarAutoresController
import br.com.zup.autor.model.Autor
import br.com.zup.autor.model.Endereco
import br.com.zup.autor.repository.AutorRepository
import br.com.zup.autor.view.DetalhesDoAutorResponse
import br.com.zup.autor.view.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class BuscaAutoresControllerTeste {

    // injetar o controller

    @field:Inject
    lateinit var buscaAutoresController: BuscarAutoresController

    // injetar um repository do autor

    @field:Inject
    lateinit var repository: AutorRepository

    lateinit var autor: Autor

    // injetar um client http que atende na rota "/"

    @field:Inject
    @field:Client(value = "/")
    lateinit var client: HttpClient

    // rodar antes de cada teste

    @BeforeEach
    fun setUp() {

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

        repository.save(autor)
    }

    // rodar depois dos testes

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }

    // 1 cenario de teste

    @DisplayName("Deve retornar os detalhes do autor")
    @Test
    fun deveRetornarOsDetalherDeUmAutor() {
        // logica aqui

        val response = client.toBlocking().exchange(
            "/autores?email=${autor.email}", DetalhesDoAutorResponse::class.java)

        // assertivas aqui

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.id, response.body()!!.id)
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(autor.endereco.localidade, response.body()!!.localidade)

    }

    // 2º cenario de teste

    @Test
    @DisplayName("Deve retornar not found quando o email a ser buscado não existe")
    fun deveRetornarNotFound() {

        // cenario

        var resposta: HttpResponse<Any> = buscaAutoresController.buscar(email = "nada@gmail.com")

        // ação

        // assertivas

        assertEquals(HttpStatus.NOT_FOUND, resposta.status)

    }

    // 3º cenario de teste

    @Test
    @DisplayName("Deve retornar 200 guando a pesquisa estiver ok")
    fun deveveRetornarOk() {

        // cenario

        var resposta: HttpResponse<Any> = buscaAutoresController.buscar("test@gmail.com")

        // açao

        // assertivas

        assertEquals(HttpStatus.OK, resposta.status)

    }

    // 4º cenario de teste

    @Test
    @DisplayName("Deve retornar 200 ok quando nao o email estiver vazio")
    fun deveRetornarOkQuandoEmailEstiverVazio() {

        // cenario

        val resposta: HttpResponse<Any> = buscaAutoresController.buscar("")

        // ação

        // assertivas
        assertEquals(HttpStatus.OK, resposta.status)

    }
}