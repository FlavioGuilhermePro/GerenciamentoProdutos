package com.java.GerenciamentoProdutos.controller;

import com.java.GerenciamentoProdutos.dto.ProdutoDTO;
import com.java.GerenciamentoProdutos.model.ProdutoModel;
import com.java.GerenciamentoProdutos.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/produtos")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/listar")
    public List<ProdutoDTO> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/listar/{id}")
    public Optional<ProdutoDTO> listarId(@PathVariable Long id){
        return produtoService.listarProdutosId(id);
    }

    @PostMapping("/criar")
    public ProdutoModel criarProduto (@RequestBody ProdutoDTO produto){
        return produtoService.criarProduto(produto);
    }
    @DeleteMapping("/deletar/{id}   ")
    public void deletarProduto(@PathVariable Long id){
         produtoService.deletarProduto(id);
    }

    @PutMapping("/atualizar/{id}")
    public Optional<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produto){
        return produtoService.atualizarProduto(id,produto);
    }
}
