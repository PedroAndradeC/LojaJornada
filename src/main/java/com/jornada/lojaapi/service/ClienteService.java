package com.jornada.lojaapi.service;

import com.jornada.lojaapi.entity.Cliente;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if(Objects.equals(cliente.getNome(), "") || cliente.getCpf() == null || cliente.getTelefone() == null) {
            throw new RegraDeNegocioException("O cliente deve ter um nome, cpf e telefone");
        }
    }
    public boolean excluir(Integer idCliente) {
        return this.clienteRepository.excluir(idCliente);
    }

    public boolean existeClientePorId(Integer idCliente) {
        List<Cliente> clientes = clienteRepository.listarPorId(idCliente);
        return !clientes.isEmpty();
    }
}

