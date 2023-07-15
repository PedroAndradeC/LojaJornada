package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.entity.Cliente;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    // GET POST PUT DELETE...
    @Autowired
    private ClienteService clienteService;

//    @GetMapping("/hello") // localhost:8080/cliente/hello
//    public String meuPrimeiroMetodo() throws Exception{
//        throw new Exception("Minha exception");
////        return "Hello World";
//    }

    @PostMapping
    public Cliente inserirCliente(@RequestBody Cliente cliente) throws RegraDeNegocioException {
        return clienteService.salvarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> retornarTodosOsClientes(){
        return clienteService.listar();
    }

    // PATH VARIABLE
    // REQUEST PARAM
    @GetMapping("/listar-por-nome") // localhost:8080/cliente/listar-por-nome?nomeDoCliente=MEUNOME
    public List<Cliente> retornarTodosOsClientes(@RequestParam("nomeDoCliente") String nome){
        return clienteService.listarPorNome(nome);
    }

    @PutMapping
    public boolean atualizarCliente(@RequestBody Cliente cliente) throws RegraDeNegocioException {
        return clienteService.editar(cliente);
    }

    @DeleteMapping("/{idCliente}")
    public boolean remover(@PathVariable("idCliente") Integer id){
        return clienteService.excluir(id);
    }
}

