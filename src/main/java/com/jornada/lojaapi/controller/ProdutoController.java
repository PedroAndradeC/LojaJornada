package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
@Validated
public class ProdutoController {

    // GET POST PUT DELETE...

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "insere um novo produto", description = "Este processo faz a inserção de um produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @PostMapping
    public Produto inserirProduto(@RequestBody @Valid Produto produto) throws RegraDeNegocioException {
        return produtoService.salvarProduto(produto);
    }

    @Operation(summary = "retorna todos os produtos", description = "Este processo retorna os produtos da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @GetMapping
    public List<Produto> retornarTodosOsProdutos(){
        return produtoService.listar();
    }

    // PATH VARIABLE
    // REQUEST PARAM
    @GetMapping("/listar-por-nome") // localhost:8080//produto/listar-por-nome?nomeDoProduto=nomeProduto
    public List<Produto> retornarTodosOsProdutos(@RequestParam("nomeDoProduto") String nome){
        return produtoService.listarPorNome(nome);
    }

    @GetMapping("/listar-por-id/{idProduto}") // localhost:8080/produto/listar-por-id/
    public List<Produto> retornarTodosOsProdutosPorId(@PathVariable("idProduto") Integer id){
        return produtoService.listarPorId(id);
    }

    @GetMapping("/listar-por-preco") // localhost:8080/produto/listar-por-preco?precoInicial=0.0&precoFinal=0.0
    public List<Produto> retornarPrecoDosProdutos(@RequestParam("precoInicial") Double precoInicial, @RequestParam("precoFinal") Double precoFinal) {
        List<Produto> produtosNoIntervalo = new ArrayList<>();
        List<Produto> todosOsProdutos = produtoService.listar();
        for (Produto produto : todosOsProdutos) {
            if (produto.getPreco() >= precoInicial && produto.getPreco() <= precoFinal) {
                produtosNoIntervalo.add(produto);
            }
        }

        return produtosNoIntervalo;
    }
    @PutMapping
    public boolean atualizarProduto(@RequestBody @Valid Produto produto) throws RegraDeNegocioException {
        return produtoService.editar(produto);
    }

    @DeleteMapping("/{idProduto}")
    public boolean remover(@PathVariable("idProduto") Integer id){
        return produtoService.excluir(id);
    }
}

