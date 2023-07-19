package com.jornada.lojaapi.service;

import com.jornada.lojaapi.entity.Vendedor;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    public Vendedor salvarVendedor(Vendedor vendedor) throws RegraDeNegocioException {
        validarVendedor(vendedor);
        // salvo no banco e retorno...
        return vendedorRepository.salvarVendedorDB(vendedor);
    }

    public boolean editar(Vendedor vendedor) throws RegraDeNegocioException {
        validarVendedor(vendedor);

        return vendedorRepository.editar(vendedor);
    }

    public List<Vendedor> listar() {
        return this.vendedorRepository.listar();
    }

    public List<Vendedor> listarPorNome(String nome) {
        return this.vendedorRepository.listarPorNome(nome);
    }

    public void validarVendedor(Vendedor vendedor) throws RegraDeNegocioException {
        if(Objects.equals(vendedor.getNome(), "") || vendedor.getCpf() == null || vendedor.getTelefone() == null) {
            throw new RegraDeNegocioException("O vendedor deve ter um nome, cpf e telefone");
        }
    }
    public boolean excluir(Integer idVendedor) {
        return this.vendedorRepository.excluir(idVendedor);
    }

    public boolean existeVendedorPorId(Integer idVendedor) {
        List<Vendedor> vendedores = vendedorRepository.listarPorId(idVendedor);
        return !vendedores.isEmpty();
    }
}

