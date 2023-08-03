package com.jornada.lojaapi.controller;

import com.jornada.lojaapi.dto.VendaDTO;
import com.jornada.lojaapi.entity.Venda;
import com.jornada.lojaapi.exception.RegraDeNegocioException;
import com.jornada.lojaapi.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venda")
@Validated
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "insere uma nova venda", description = "Este processo faz a inserção de uma venda na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deu certo"),
            @ApiResponse(responseCode = "400", description = "erro na validação dos dados"),
            @ApiResponse(responseCode = "500", description = "erro no servidor")
    })

    @PostMapping
    public VendaDTO efetuarVenda(@RequestBody @Valid VendaDTO venda) throws RegraDeNegocioException {
        return vendaService.efetuarVenda(venda);
    }
}
