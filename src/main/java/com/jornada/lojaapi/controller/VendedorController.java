package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.entity.Vendedor;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.service.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
@Validated
public class VendedorController {
    // GET POST PUT DELETE...
    @Autowired
    private VendedorService vendedorService;

//    @GetMapping("/hello") // localhost:8080/cliente/hello
//    public String meuPrimeiroMetodo() throws Exception{
//        throw new Exception("Minha exception");
////        return "Hello World";
//    }

    @Operation(summary = "insere um novo vendedor", description = "Este processo faz a inserção de um vendedor na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @PostMapping
    public Vendedor inserirVendedor(@RequestBody @Valid Vendedor vendedor) throws RegraDeNegocioException {
        return vendedorService.salvarVendedor(vendedor);
    }

    @Operation(summary = "retorna todos os vendedores", description = "Este processo retorna os vendedores da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })


    @GetMapping
    public List<Vendedor> retornarTodosOsVendedores(){
        return vendedorService.listar();
    }

    // PATH VARIABLE
    // REQUEST PARAM
    @GetMapping("/listar-por-nome") // localhost:8080/Vendedor/listar-por-nome?nomeDoVendedor=NOME
    public List<Vendedor> retornarTodosOsVendedores(@RequestParam("nomeDoVendedor") String nome){
        return vendedorService.listarPorNome(nome);
    }

    @PutMapping
    public boolean atualizarVendedor(@RequestBody @Valid Vendedor vendedor) throws RegraDeNegocioException {
        return vendedorService.editar(vendedor);
    }

    @DeleteMapping("/{idVendedor}")
    public boolean remover(@PathVariable("idVendedor") Integer id){
        return vendedorService.excluir(id);
    }
}

