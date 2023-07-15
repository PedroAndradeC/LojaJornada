package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.entity.Vendedor;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    // GET POST PUT DELETE...
    @Autowired
    private VendedorService vendedorService;

//    @GetMapping("/hello") // localhost:8080/cliente/hello
//    public String meuPrimeiroMetodo() throws Exception{
//        throw new Exception("Minha exception");
////        return "Hello World";
//    }

    @PostMapping
    public Vendedor inserirVendedor(@RequestBody Vendedor vendedor) throws RegraDeNegocioException {
        return vendedorService.salvarVendedor(vendedor);
    }

    @GetMapping
    public List<Vendedor> retornarTodosOsVendedores(){
        return vendedorService.listar();
    }

    // PATH VARIABLE
    // REQUEST PARAM
    @GetMapping("/listar-por-nome") // localhost:8080/Vendedor/listar-por-nome?nomeDoVendedor=NOME
    public List<Vendedor> retornarTodosOsVendedores(@RequestParam("nomeDoVendedor") String nome){
        return vendedorService.listarPorNome(nome);
    }

    @PutMapping
    public boolean atualizarVendedor(@RequestBody Vendedor vendedor) throws RegraDeNegocioException {
        return vendedorService.editar(vendedor);
    }

    @DeleteMapping("/{idVendedor}")
    public boolean remover(@PathVariable("idVendedor") Integer id){
        return vendedorService.excluir(id);
    }
}

