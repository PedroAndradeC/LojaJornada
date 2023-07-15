package com.jornada.lojaapi.service;

import com.jornada.lojaapi.entity.Cliente;
import com.jornada.lojaapi.entity.Produto;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) throws RegraDeNegocioException {
        validarCliente(cliente);
        // salvo no banco e retorno...
        return clienteRepository.salvarClienteDB(cliente);
    }

    public boolean editar(Cliente cliente) throws RegraDeNegocioException {
        validarCliente(cliente);

        return clienteRepository.editar(cliente);
    }

    public List<Cliente> listar() {
        return this.clienteRepository.listar();
    }

    public List<Cliente> listarPorNome(String nome) {
        return this.clienteRepository.listarPorNome(nome);
    }

    public void validarCliente(Cliente cliente) throws RegraDeNegocioException {
        if(cliente.getNome() == null) {
            throw new RegraDeNegocioException("O cliente deve ter um nome");
        }
    }
    public boolean excluir(Integer idCliente) {
        return this.clienteRepository.excluir(idCliente);
    }
}

