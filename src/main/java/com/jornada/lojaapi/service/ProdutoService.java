package com.jornada.lojaapi.service;

import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // BEAN
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) throws RegraDeNegocioException {
        validarProduto(produto);

        Produto produtoSalvo = produtoRepository.salvarProdutoDB(produto);
        return produtoSalvo;
    }

    public boolean editar(Produto produto) throws RegraDeNegocioException {
        validarProduto(produto);

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

    public List<Produto> listarPorPreco(Integer id) {
        return this.produtoRepository.listarPorPreco(id);
    }

    public boolean existeProdutoPorId(Integer id) {
        List<Produto> produtos = produtoRepository.listarPorId(id);
        return !produtos.isEmpty();
    }

    public int quantidadeDisponivel(Integer id) {
        List<Produto> produtos = produtoRepository.listarPorId(id);
        if(!produtos.isEmpty()) {
            Produto produto = produtos.get(0);
            return produto.getQuantidade();
        }
        return 0;
    }
    public void validarProduto(Produto produto) throws RegraDeNegocioException {
        if(produto.getQuantidade() > 1000) {
            throw new RegraDeNegocioException("Não deve passar de 1000 produtos");
        }
    }

    public boolean excluir(Integer id) {
        return this.produtoRepository.excluir(id);
    }
}

