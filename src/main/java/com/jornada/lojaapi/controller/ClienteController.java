package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.entity.Cliente;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Validated
public class ClienteController {

    // GET POST PUT DELETE...
    @Autowired
    private ClienteService clienteService;

//    @GetMapping("/hello") // localhost:8080/cliente/hello
//    public String meuPrimeiroMetodo() throws Exception{
//        throw new Exception("Minha exception");
////        return "Hello World";
//    }

    @Operation(summary = "insere um novo cliente", description = "Este processo faz a inserção de um cliente na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @PostMapping
    public Cliente inserirCliente(@RequestBody @Valid Cliente cliente) throws RegraDeNegocioException {
        return clienteService.salvarCliente(cliente);
    }

    @Operation(summary = "retorna todos os clientes", description = "Este processo retorna os clientes da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })


    @GetMapping
    public List<Cliente> retornarTodosOsClientes(){
        return clienteService.listar();
    }

    // PATH VARIABLE
    // REQUEST PARAM
    @GetMapping("/listar-por-nome") // localhost:8080/cliente/listar-por-nome?nomeDoCliente=MEUNOME
    public List<Cliente> retornarTodosOsClientes(@RequestParam("nomeDoCliente") String nome){
        return clienteService.listarPorNome(nome);
    }

    @PutMapping
    public boolean atualizarCliente(@RequestBody @Valid Cliente cliente) throws RegraDeNegocioException {
        return clienteService.editar(cliente);
    }

    @DeleteMapping("/{idCliente}")
    public boolean remover(@PathVariable("idCliente") Integer id){
        return clienteService.excluir(id);
    }
}

