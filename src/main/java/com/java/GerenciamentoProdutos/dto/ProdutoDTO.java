package com.java.GerenciamentoProdutos.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private Long categoriaId;
    private String categoriaNome;
    private String descricao;
    private double preco;
    private int quantidade;
}
