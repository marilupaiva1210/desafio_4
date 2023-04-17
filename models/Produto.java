package com.sprint4.desafio.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {

    private Integer codigo;
    private String descricao;
    private BigDecimal precovenda;

    private Impostos imposto;

}
