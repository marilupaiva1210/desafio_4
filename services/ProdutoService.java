package com.sprint4.desafio.services;

import com.sprint4.desafio.models.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    //faz cadastro de novos produtos
    public void adicionar(Produto produtoQueSeraSalvo) {

        //adiciona o produto recebido
        produtos.add(produtoQueSeraSalvo);

    }

    //faz a listagem
    public List<Produto> listarTudo() {

        //retornando lista produtos vinda da classe Produtos
        return produtos;

    }

    //vai buscar por um codigo recebido
    public Produto buscarPorCodigo(Integer codigo) {
                                    //cod recebido

        return produtos.stream().filter(p -> codigo.equals(p.getCodigo())).findFirst().orElseThrow();
                                                                  //achar o primeiro     //ou lance um erro
    }

    //atualizar codigo de produto
    public void update(Integer codigo, Produto produto) {

        //remoção de codigo recebido
        remover(codigo);

        //adição de produto adicionado
        adicionar(produto);

    }

    // remove o imposto
    public void remover(Integer codigo) {

        //produto vai receber um codigo do valor recebido
        Produto produtoPesquisa = (Produto) buscarPorCodigo(codigo);

        //se for diferente de nulo, o produto vai ser deletado
        if (produtoPesquisa != null) {
            produtos.remove(produtoPesquisa);

        }
    }
}