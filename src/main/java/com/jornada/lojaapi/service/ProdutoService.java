package com.jornada.lojaapi.service;

import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) throws Exception {

        return produtoRepository.salvarProdutoDB(produto);
    }

    public boolean editar(Produto produto) throws Exception {

        return produtoRepository.editar(produto);
    }

    public List<Produto> listar() {
        return this.produtoRepository.listar();
    }

    public List<Produto> listarPorNome(String nome) {
        return this.produtoRepository.listarPorNome(nome);
    }

    public List<Produto> listarPorId(Integer id) {
        return this.produtoRepository.listarPorId(id);
    }

    public boolean excluir(Integer id) {
        return this.produtoRepository.excluir(id);
    }
}

