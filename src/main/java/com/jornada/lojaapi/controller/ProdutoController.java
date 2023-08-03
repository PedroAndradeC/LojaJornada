package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.dto.ProdutoDTO;
import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
@Validated
@Slf4j
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
    public ProdutoDTO inserirProduto(@RequestBody @Valid ProdutoDTO produtoDTO) throws RegraDeNegocioException {
        log.info("produto foi inserido");
        return produtoService.salvarProduto(produtoDTO);
    }

    @Operation(summary = "retorna todos os produtos", description = "Este processo retorna os produtos da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @GetMapping
    public List<ProdutoDTO> retornarTodosOsProdutos(){
        return produtoService.listar();
    }

    @Operation(summary = "retorna um produto por nome", description = "Este processo retorna o produto por nome da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    // PATH VARIABLE
    // REQUEST PARAM
    @GetMapping("/listar-por-nome") // localhost:8080//produto/listar-por-nome?nomeDoProduto=nomeProduto
    public List<ProdutoDTO> retornarTodosOsProdutos(@RequestParam("nomeDoProduto") String nome){
        return produtoService.listarPorNome(nome);
    }

    @Operation(summary = "retorna um produto por id", description = "Este processo retorna o produto por id da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @GetMapping("/listar-por-id/{idProduto}") // localhost:8080/produto/listar-por-id/
    public List<ProdutoDTO> retornarTodosOsProdutosPorId(@PathVariable("idProduto") Integer id){
        return produtoService.listarPorId(id);
    }

    @Operation(summary = "retorna um produto por preço inicial e final", description = "Este processo retorna o produto por preço inicial e final da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @GetMapping("/listar-por-preco") // localhost:8080/produto/listar-por-preco?precoInicial=0.0&precoFinal=0.0
    public List<ProdutoDTO> retornarPrecoDosProdutos(@RequestParam("precoInicial") Double precoInicial, @RequestParam("precoFinal") Double precoFinal) {
        List<ProdutoDTO> produtosNoIntervalo = new ArrayList<>();
        List<ProdutoDTO> todosOsProdutos = produtoService.listar();
        for (ProdutoDTO produto : todosOsProdutos) {
            if (produto.getPreco() >= precoInicial && produto.getPreco() <= precoFinal) {
                produtosNoIntervalo.add(produto);
            }
        }

        return produtosNoIntervalo;
    }

    @Operation(summary = "atualiza um produto", description = "Este processo faz a atualização de um produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @PutMapping
    public boolean atualizarProduto(@RequestBody @Valid ProdutoDTO produto) throws RegraDeNegocioException {
        return produtoService.editar(produto);
    }

    @Operation(summary = "deleta um produto", description = "Este processo faz a remoção de um produto na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @DeleteMapping("/{idProduto}")
    public boolean remover(@PathVariable("idProduto") Integer id){
        return produtoService.excluir(id);
    }
}

