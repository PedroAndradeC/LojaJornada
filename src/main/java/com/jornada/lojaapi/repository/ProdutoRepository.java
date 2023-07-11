package com.jornada.lojaapi.repository;

import com.jornada.lojaapi.entity.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private static List<Produto> produtos = new ArrayList<>();
    private static int nextId = 1;

    public Produto salvarProdutoDB(Produto produto) {
        produto.setIdProduto(nextId++);
        produtos.add(produto);
        return produto;
    }

    public boolean editar(Produto produto) {
        for (Produto p : produtos) {
            if (p.getIdProduto().equals(produto.getIdProduto())) {
                p.setNome(produto.getNome());
                p.setPreco(produto.getPreco());
                return true;
            }
        }
        return false;
    }

    public List<Produto> listar() {
        return produtos;
    }

    public List<Produto> listarPorNome(String nome) {
        List<Produto> produtosPorNome = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                produtosPorNome.add(p);
            }
        }
        return produtosPorNome;
    }

    public List<Produto> listarPorId(Integer id) {
        List<Produto> produtosPorId = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getIdProduto().equals(id)) {
                produtosPorId.add(p);
            }
        }
        return produtosPorId;
    }

    public boolean excluir(Integer id) {
        for (Produto p : produtos) {
            if (p.getIdProduto().equals(id)) {
                produtos.remove(p);
                return true;
            }
        }
        return false;
    }
}
