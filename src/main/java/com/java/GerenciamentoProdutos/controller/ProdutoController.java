package com.java.GerenciamentoProdutos.controller;

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
    public List<ProdutoModel> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/listar/{id}")
    public Optional<ProdutoModel> listarId(@PathVariable Long id){
        return produtoService.listarProdutosId(id);
    }

    @PostMapping("/criar")
    public ProdutoModel criarProduto (@RequestBody ProdutoModel produto){
        return produtoService.criarProduto(produto);
    }
    @DeleteMapping("/deletar/{id}   ")
    public void deletarProduto(@PathVariable Long id){
         produtoService.deletarProduto(id);
    }

    @PutMapping("/atualizar/{id}")
    public Optional<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produto){
        return produtoService.atualizarProduto(id,produto);
    }
}
