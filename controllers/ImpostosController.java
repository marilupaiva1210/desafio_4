package com.sprint4.desafio.controllers;

import com.sprint4.desafio.models.Impostos;
import com.sprint4.desafio.services.ImpostosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/impostos")
public class ImpostosController {
    @Autowired
    ImpostosService service;

    //Cadastro - CREATE

    @PostMapping(value = "/novo")
    @Operation(summary = "Cadastro dos impostos", description = "API que faz o cadastro dos impostos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity cadastrar(@RequestBody Impostos impostos) {

        try {
            service.adicionar(impostos);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Não foi possível cadastrar o imposto.", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>("imposto cadastrado!", HttpStatus.CREATED);

    }

    // Listagem - READ
    @GetMapping()
    @Operation(summary = "Listagem de todos os impostos", description = "API que lista todos os impostos" )
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listar() {

        try{
            return new ResponseEntity(service.listarTudo(), HttpStatus.OK);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);

        }
    }

    //Listagem por código - READ
    @GetMapping(value = "/{codigo}")
    @Operation(summary = "Listagem feita pelo código informado", description = "API que recebe um código e o lista")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(Integer codigo) {

        try {
            return new ResponseEntity(service.buscarPorCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Código inválido! Tente novamente.", HttpStatus.BAD_REQUEST);

        }
    }

    //Alteração do codigo já cadastrado - UPDATE
    @PutMapping(value = "/{codigo}")
    @Operation(summary = "Alteração do seu imposto", description = "Altera o seu imposto conforme o código é informado")

    public ResponseEntity alteracao(@PathVariable Integer codigo, @RequestBody Impostos impostos) {

        try {
            service.update(codigo, impostos);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(impostos, HttpStatus.OK);

    }

    //Alteração de alíquota já cadastrado - UPDATE
    @PutMapping(value = "/{codigo}/{aliquota}")
    @Operation(summary = "Alteração da sua alíquota", description = "Altera o sua alíquota conforme o código é informado")

    public ResponseEntity alteracaoDeAliquota(@RequestBody Double aliquota, @PathVariable Integer codigo) {

        service.atualizar(aliquota, codigo);
        return new ResponseEntity("Imposto com o código " + codigo + " foi alterado!", HttpStatus.OK);

    }

    //Exclusão - DELETE
    @DeleteMapping(value = "/{codigo}")
    @Operation(summary = "Exclusão do imposto", description = "Deleta o seu imposto conforme o código que é informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity exclusao(@PathVariable Integer codigo) {

        try {
            service.remover(codigo);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity("Imposto deletado!", HttpStatus.OK);

    }

    @PostMapping(value = "/calcular/{codigo}")
    @Operation(summary = "Cálcula o valor do imposto", description = "Dado o valor base, apresenta o valor calculado do imposto escolhido pelo usuário")

    public ResponseEntity calculo(@PathVariable Integer codigo, @RequestParam Double valor) {

        try {
            return new ResponseEntity(service.valorCalculado(codigo, valor), HttpStatus.OK);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Não foi possível fazer o cálculo", HttpStatus.BAD_REQUEST);

        }
    }
}


