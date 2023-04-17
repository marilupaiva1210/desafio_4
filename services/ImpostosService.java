package com.sprint4.desafio.services;

import com.sprint4.desafio.models.Impostos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpostosService {

    private List<Impostos> impostos = new ArrayList<>();

    //faz cadastro de novos impostos
    public void adicionar(Impostos impostoSalvo) {


        //adiciona o imposto recebido
        impostos.add(impostoSalvo);

    }

    //faz a listagem
    public List<Impostos> listarTudo() {

        //retornando lista impostos vinda da classe Impostos
        return impostos;

    }

    //vai buscar ppor um codigo recebido
    public Impostos buscarPorCodigo(Integer codigo) {
                                    //cod recebido

        return impostos.stream().filter(i-> codigo.equals(i.getCodigo())).findFirst().orElseThrow();
                                                                    //achar o primeiro     //ou lance um erro
    }

    //atualizar codigo de imposto
    public void update(Integer codigo, Impostos imposto) {

        //remoção de codigo recebido
        remover(codigo);

        //adição de imposto adicionado
        adicionar(imposto);

    }

    //remover o antigo, e add um novo
    public void atualizar(Double aliquotas, Integer codigo) {
        Impostos impostoAchado = buscarPorCodigo(codigo);
        if (impostoAchado != null) {

            //objeto q guarda novo imposto
            Impostos imposto = new Impostos();

            //definir os valores do novo imposto
            imposto.setAliquotas(aliquotas);
            imposto.setCodigo(codigo);

            //removendo antigo, add o novo
            impostos.remove(impostoAchado);
            impostos.add(imposto);

        }
    }

    // remove o imposto
    public void remover(Integer codigo) {

        //imposto vai receber um codigo do valor recebido
        Impostos impostoProcura = buscarPorCodigo(codigo);

        //se for diferente de nulo, o imposto vai ser deletado
        if (impostoProcura != null) {
            impostos.remove(impostoProcura);

        }
    }

    public Double valorCalculado(Integer codigo, Double valor) {

        Double aliquotaAchada = buscarPorCodigo(codigo).getAliquotas();
        return valor * aliquotaAchada / 100;

    }
}
