package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.repository.ProdutoRepository;
import com.jornada.lojaapi.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    // GET POST PUT DELETE...
    private final ProdutoRepository produtoRepository = new ProdutoRepository();
    private final ProdutoService produtoService = new ProdutoService(produtoRepository);

    @PostMapping
    public Produto inserirProduto(@RequestBody Produto produto) throws Exception{
        return produtoService.salvarProduto(produto);
    }

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
    public boolean atualizarProduto(@RequestBody Produto produto) throws Exception {
        return produtoService.editar(produto);
    }

    @DeleteMapping("/{idProduto}")
    public boolean remover(@PathVariable("idProduto") Integer id){
        return produtoService.excluir(id);
    }
}
