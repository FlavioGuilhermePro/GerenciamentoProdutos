package com.java.GerenciamentoProdutos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table (name = "tb_produto")
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    private int quantidade;


}
