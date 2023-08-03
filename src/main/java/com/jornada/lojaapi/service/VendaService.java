package com.jornada.lojaapi.service;

import com.jornada.lojaapi.dto.VendaDTO;
import com.jornada.lojaapi.entity.Venda;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.mapper.VendaMapper;
import com.jornada.lojaapi.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final VendedorService vendedorService;
    private final VendaMapper vendaMapper;

    public VendaDTO efetuarVenda(VendaDTO venda) throws RegraDeNegocioException {
        validarVenda(venda);

        Venda vendaConvertido = vendaMapper.converteParaEntity(venda);
        if (!produtoService.existeProdutoPorId(venda.getIdProduto())) {
            throw new RegraDeNegocioException("Produto não encontrado.");
        }
        if (!clienteService.existeClientePorId(venda.getIdCliente())) {
            throw new RegraDeNegocioException("Cliente não encontrado.");
        }
        if (!vendedorService.existeVendedorPorId(venda.getIdVendedor())) {
            throw new RegraDeNegocioException("Vendedor não encontrado.");
        }
        vendaRepository.salvarVendaDB(vendaConvertido);
        return venda;
    }

    private void validarVenda(VendaDTO venda) throws RegraDeNegocioException {

        if (venda.getIdProduto() == null || venda.getIdProduto() <= 0) {
            throw new RegraDeNegocioException("Produto não encontrado.");
        }
        if (venda.getIdCliente() == null || venda.getIdCliente() <= 0) {
            throw new RegraDeNegocioException("Cliente não encontrado.");
        }
        if (venda.getIdVendedor() == null || venda.getIdVendedor() <= 0) {
            throw new RegraDeNegocioException("Vendedor não encontrado.");
        }
        if (venda.getQuantidade() == null || venda.getQuantidade() <= 0) {
            throw new RegraDeNegocioException("Quantidade inválida.");
        }
        int quantidade = produtoService.quantidadeDisponivel(venda.getQuantidade());
        if(venda.getQuantidade() > quantidade || venda.getQuantidade() >= quantidade) {
            throw new RegraDeNegocioException("Quantidade insuficiente em estoque.");
        }
    }
}
