package br.com.zup

import br.com.zup.autor.controller.CadastraAutorController
import br.com.zup.autor.model.Autor
import br.com.zup.autor.model.Endereco
import br.com.zup.autor.repository.AutorRepository
import br.com.zup.autor.view.CadastraAutorRequest
import br.com.zup.autor.view.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.micronaut.validation.validator.constraints.ConstraintValidator
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import javax.inject.Inject
import javax.validation.ConstraintViolationException

@MicronautTest
class CadastraAutorControllerTest {

    lateinit var autor: Autor

    // injetar um controller

    @field:Inject
    lateinit var cadastraAutorController: CadastraAutorController

    // injetar um repository

    @field:Inject
    lateinit var repository: AutorRepository

    // rodar antes de cada teste --------------------------------------------------------------
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

    // rodar depois de cada teste -----------------------------------------------------------

    @AfterEach
    internal fun tearDown() {
        repository.deleteAll()
    }

    // 1 cenario de teste / testando a inserção dos dados---------------------------------

    @Test
    @DisplayName("Deve inserir um autor")
    fun deveInserirUmNovoAutor() {

        // cenario

        // -> 1º recebimento da request  (validado) -> 2º vai na api externa buscar o endereço
        // -> 3º entidade recebe os dados -> 4º salva no banco -> 5º retorna 201 created

        // ação


        // assertivas aqui
        Assertions.assertEquals(1, repository.count())

    }

    // 2 cenario / testando a request ----------------------------------------------------------

    @Test
    @DisplayName("deve retornar erro de email em branco")
    fun deveRetornarErroDeEmail(){

        // cenario

        var request: CadastraAutorRequest = CadastraAutorRequest(
            nome = "Jose",
            email = "",
            descricao = "muito rigido",
            numero = "10",
            cep = "58410505"
        )

        // ação

        val exception = assertThrows<ConstraintViolationException> {
            cadastraAutorController.insert(request = request)
        }

        // assertivas

        assertEquals("insert.request.email: não deve estar em branco",
        exception.message
        )

    }

    // 3º cenario / testando a request ----------------------------------------------------------

    @Test
    @DisplayName("Deve retornar erro ao tentar inserir um autor com email ja cadastrado")
    fun deveRetornarErroEmailUnico() {

        // cenario

        var request: CadastraAutorRequest = CadastraAutorRequest(
            nome = "Jose",
            email = "test@gmail.com",
            descricao = "muito rigido",
            numero = "10",
            cep = "58410505"
        )

        // ação

        val exception = assertThrows<ConstraintViolationException> {
            cadastraAutorController.insert(request = request)
        }

        // assertiva

        assertEquals("insert.request.email: Validation error, este dado é unico e já existe cadastro no banco",
            exception.message
        )

    }

    // 4º cenario / testando a request ----------------------------------------------------------

    @Test
    @DisplayName("Deve retornar erro, descrição no maximo 400 caracter")
    fun deveRetornarErroExcessoTamanhoDaDescricao() {

        // cenario

        var request: CadastraAutorRequest = CadastraAutorRequest(
            nome = "Jose",
            email = "jose@gmail.com",
            descricao = "ggiugiuguguuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuusdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaauuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuurewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwrerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr ",
            numero = "10",
            cep = "58410505"
        )

        // ação

        val exception = assertThrows<ConstraintViolationException> {
            cadastraAutorController.insert(request = request)
        }

        // assertiva

        assertEquals("insert.request.descricao: tamanho deve ser entre 0 e 400",
            exception.message
        )

    }

    // 5º cenario / testando a request -------------------------------------------------------

    @DisplayName("Deve retornar erro quando tentar inserir email de formato invalido")
    @Test
    fun deveRetornarErroQuandoOformatoEmailInvalido() {

        // cenario

        var request: CadastraAutorRequest = CadastraAutorRequest(
            nome = "Jose",
            email = "josegmail.com",
            descricao = "muito rigido",
            numero = "10",
            cep = "58410505"
        )

        // ação

        val exception = assertThrows<ConstraintViolationException> {
            cadastraAutorController.insert(request = request)
        }
        // assertivas

        assertEquals("insert.request.email: deve ser um endereço de e-mail bem formado",
        exception.message
        )
    }

    // 6º cenario / testando a request------------------------------------------------------

    @Test
    @DisplayName("Deve retornar 201 created guando os dados forem validados ")
    fun deveRetornarCreatedQuandoOsDadosForemvalidados() {

        // cenario

        var request: CadastraAutorRequest = CadastraAutorRequest(
            nome = "luana",
            email = "luana@email.com",
            descricao = "Uma autora bonita",
            numero = "154",
            cep = "58410505"
        )

        // ação

        var response: HttpResponse<Any> = cadastraAutorController.insert(request)

        // assertivas

        assertEquals(HttpStatus.CREATED, response.status)

    }

}