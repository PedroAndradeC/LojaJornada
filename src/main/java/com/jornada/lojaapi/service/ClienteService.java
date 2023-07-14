package com.jornada.lojaapi.service;

import com.jornada.lojaapi.entity.Cliente;
import com.jornada.lojaapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        // salvo no banco e retorno...
        return clienteRepository.salvarClienteDB(cliente);
    }

    public boolean editar(Cliente cliente) {

        return clienteRepository.editar(cliente);
    }

    public List<Cliente> listar() {
        return this.clienteRepository.listar();
    }

    public List<Cliente> listarPorNome(String nome) {
        return this.clienteRepository.listarPorNome(nome);
    }

    public boolean excluir(Integer idCliente) {
        return this.clienteRepository.excluir(idCliente);
    }
}

