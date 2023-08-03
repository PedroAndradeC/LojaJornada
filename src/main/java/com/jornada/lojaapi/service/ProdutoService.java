package com.jornada.lojaapi.service;

import com.jornada.lojaapi.dto.ProdutoDTO;
import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.entity.Vendedor;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.mapper.ProdutoMapper;
import com.jornada.lojaapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // BEAN
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) throws RegraDeNegocioException {
        validarProduto(produtoDTO);
        Produto produtoConvertido = produtoMapper.converteParaEntity(produtoDTO);
        Produto produtoSalvo =  produtoRepository.salvarProdutoDB(produtoConvertido);
        return produtoMapper.converteParaDto(produtoSalvo);
    }

    public boolean editar(ProdutoDTO produtoDTO) throws RegraDeNegocioException {
        validarProduto(produtoDTO);
        Produto produtoConvertido = produtoMapper.converteParaEntity(produtoDTO);

        return produtoRepository.editar(produtoConvertido);
    }

    public List<ProdutoDTO> listar() {
        List<ProdutoDTO> listaDeDtos = this.produtoRepository.listar().stream()
                .map(entidade -> produtoMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public List<ProdutoDTO> listarPorNome(String nome) {
        List<ProdutoDTO> listaDeDtos = this.produtoRepository.listarPorNome(nome).stream()
                .map(entidade -> produtoMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public List<ProdutoDTO> listarPorId(Integer id) {
        List<ProdutoDTO> listaDeDtos = this.produtoRepository.listarPorId(id).stream()
                .map(entidade -> produtoMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public List<ProdutoDTO> listarPorPreco(Integer id) {
        List<ProdutoDTO> listaDeDtos = this.produtoRepository.listarPorPreco(id).stream()
                .map(entidade -> produtoMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public boolean existeProdutoPorId(Integer id) {
        List<ProdutoDTO> listaDeDtos = this.produtoRepository.listarPorPreco(id).stream()
                .map(entidade -> produtoMapper.converteParaDto(entidade))
                .toList();
        return !listaDeDtos.isEmpty();
    }

    public int quantidadeDisponivel(Integer id) {
        List<ProdutoDTO> listaDeDtos = this.produtoRepository.listarPorPreco(id).stream()
                .map(entidade -> produtoMapper.converteParaDto(entidade))
                .toList();
        if (!listaDeDtos.isEmpty()) {
            ProdutoDTO produtoDTO = listaDeDtos.get(0);
            return produtoDTO.getQuantidade();
        }
        return 0;
    }
    public void validarProduto(ProdutoDTO produtoDTO) throws RegraDeNegocioException {
        if(produtoDTO.getQuantidade() > 1000) {
            throw new RegraDeNegocioException("Não deve passar de 1000 produtos");
        }
    }

    public boolean excluir(Integer id) {
        return this.produtoRepository.excluir(id);
    }
}

