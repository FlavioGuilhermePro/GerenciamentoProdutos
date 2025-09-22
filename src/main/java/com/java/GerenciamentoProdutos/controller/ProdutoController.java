package com.java.GerenciamentoProdutos.controller;

import com.java.GerenciamentoProdutos.dto.ProdutoDTO;
import com.java.GerenciamentoProdutos.model.ProdutoModel;
import com.java.GerenciamentoProdutos.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        List<ProdutoDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<ProdutoDTO> listarId(@PathVariable Long id){
        Optional<ProdutoDTO> produtos = produtoService.listarProdutosId(id);
        if(produtos.isPresent()){
            return ResponseEntity.ok(produtos.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<ProdutoModel> criarProduto (@RequestBody ProdutoDTO produto){
        ProdutoModel novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
    @DeleteMapping("/deletar/{id}   ")
    public void deletarProduto(@PathVariable Long id){
         produtoService.deletarProduto(id);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produto){
        Optional<ProdutoModel> produtoAtualizado = produtoService.atualizarProduto(id,produto);
        if(produtoAtualizado.isPresent()){
            return ResponseEntity.ok(produtoAtualizado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
