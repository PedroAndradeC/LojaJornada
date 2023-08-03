package com.jornada.lojaapi.service;

import com.jornada.lojaapi.dto.ProdutoDTO;
import com.jornada.lojaapi.dto.VendedorDTO;
import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.entity.Vendedor;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.mapper.VendedorMapper;
import com.jornada.lojaapi.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final VendedorMapper vendedorMapper;

    public VendedorDTO salvarVendedor(VendedorDTO vendedorDTO) throws RegraDeNegocioException {
        validarVendedor(vendedorDTO);
        Vendedor vendedorConvertido = vendedorMapper.converteParaEntity(vendedorDTO);
        // salvo no banco e retorno...
        Vendedor vendedorSalvo =  vendedorRepository.salvarVendedorDB(vendedorConvertido);
        return vendedorMapper.converteParaDto(vendedorSalvo);
    }

    public boolean editar(VendedorDTO vendedorDTO) throws RegraDeNegocioException {
        validarVendedor(vendedorDTO);
        Vendedor vendedorConvertido = vendedorMapper.converteParaEntity(vendedorDTO);

        return vendedorRepository.editar(vendedorConvertido);
    }

    public List<VendedorDTO> listar() {
        List<VendedorDTO> listaDeDtos = this.vendedorRepository.listar().stream()
                .map(entidade -> vendedorMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public List<VendedorDTO> listarPorNome(String nome) {
        List<VendedorDTO> listaDeDtos = this.vendedorRepository.listarPorNome(nome).stream()
                .map(entidade -> vendedorMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public void validarVendedor(VendedorDTO vendedorDTO) throws RegraDeNegocioException {
        if(Objects.equals(vendedorDTO.getNome(), "") || vendedorDTO.getCpf() == null || vendedorDTO.getTelefone() == null) {
            throw new RegraDeNegocioException("O vendedor deve ter um nome, cpf e telefone");
        }
    }
    public boolean excluir(Integer idVendedor) {
        return this.vendedorRepository.excluir(idVendedor);
    }

    public boolean existeVendedorPorId(Integer idVendedor) {
        List<VendedorDTO> listaDeDtos = this.vendedorRepository.listarPorId(idVendedor).stream()
                .map(entidade -> vendedorMapper.converteParaDto(entidade))
                .toList();
        return !listaDeDtos.isEmpty();
    }
}

