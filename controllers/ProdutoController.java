package com.sprint4.desafio.controllers;


import com.sprint4.desafio.models.Produto;
import com.sprint4.desafio.services.ImpostosService;
import com.sprint4.desafio.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping (value = "/produtos")
public class ProdutoController {

    @Autowired ProdutoService service;

    @Autowired ImpostosService impostosService;

    //Cadastro - CREATE
    @PostMapping(value = "/novo")
    @Operation(summary = "Cadastro dos produtos", description = "API que faz o cadastro dos produtos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity novoProduto(@RequestParam Integer codigo, @RequestBody Produto produto) {

        produto.setImposto(impostosService.buscarPorCodigo(codigo));

        try {
            service.adicionar(produto);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Não foi possível cadastrar o produto.", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity("produto cadastrado! O código do imposto é: " + codigo, HttpStatus.CREATED);

    }

    // Listagem - READ
    @GetMapping()
    @Operation(summary = "Listagem de todos os produtos", description = "API que lista todos os produtos" )
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarTodos() {

        try {
            return new ResponseEntity(service.listarTudo(), HttpStatus.OK);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);

        }
    }

    //Listagem por código - READ
    @GetMapping(value = "/{codigo}")
    @Operation(summary = "Listagem feita pelo código informado", description = "API que recebe um código e o lista")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(service.buscarPorCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Código inválido! Tente novamente.", HttpStatus.BAD_REQUEST);

        }
    }

    //Alteração - UPDATE
    @PutMapping(value = "/{codigo}")
    @Operation(summary = "Alteração do seu produto", description = "Altera o seu produto conforme o código é informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity Alterar(@PathVariable Integer codigo ,@RequestBody Produto produto) {

        try {
            service.update(codigo, produto);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity(produto, HttpStatus.OK);

    }

    //Exclusão - DELETE
    @DeleteMapping(value ="/{codigo}")
    @Operation(summary = "Exclusão do produto", description = "Deleta o seu produto conforme o código que é informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity Remover(@PathVariable Integer codigo) {

        try {
            service.remover(codigo);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity("Produto com o codigo " + codigo + " foi deletado!", HttpStatus.OK);

    }
}
