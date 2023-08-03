package com.jornada.lojaapi.service;

import com.jornada.lojaapi.dto.ClienteDTO;
import com.jornada.lojaapi.entity.Cliente;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.mapper.ClienteMapper;
import com.jornada.lojaapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteDTO salvarCliente(ClienteDTO clienteDTO) throws RegraDeNegocioException {
        validarCliente(clienteDTO);
        Cliente clienteConvertido = clienteMapper.converteParaEntity(clienteDTO);
        // salvo no banco e retorno...
        Cliente clienteSalvo = clienteRepository.salvarClienteDB(clienteConvertido);

        // converte entity para dto

        return clienteMapper.converteParaDto(clienteSalvo);
    }

    public boolean editar(ClienteDTO clienteDTO) throws RegraDeNegocioException {
        validarCliente(clienteDTO);
        Cliente clienteConvertido = clienteMapper.converteParaEntity(clienteDTO);

        return clienteRepository.editar(clienteConvertido);
    }

    public List<ClienteDTO> listar() {
        List<ClienteDTO> listaDeDtos = this.clienteRepository.listar().stream()
                .map(entidade -> clienteMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public List<ClienteDTO> listarPorNome(String nome) {
        List<ClienteDTO> listaDeDtos = this.clienteRepository.listarPorNome(nome).stream()
                .map(entidade -> clienteMapper.converteParaDto(entidade))
                .toList();
        return listaDeDtos;
    }

    public void validarCliente(ClienteDTO clienteDTO) throws RegraDeNegocioException {
        if(Objects.equals(clienteDTO.getNome(), "") || clienteDTO.getCpf() == null || clienteDTO.getTelefone() == null) {
            throw new RegraDeNegocioException("O cliente deve ter um nome, cpf e telefone");
        }
    }
    public boolean excluir(Integer idCliente) {
        return this.clienteRepository.excluir(idCliente);
    }

    public boolean existeClientePorId(Integer idCliente) {
        List<ClienteDTO> listaDeDtos = this.clienteRepository.listarPorId(idCliente).stream()
                .map(entidade -> clienteMapper.converteParaDto(entidade))
                .toList();
        return !listaDeDtos.isEmpty();
    }
}

