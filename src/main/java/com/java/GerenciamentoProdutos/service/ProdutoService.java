package com.java.GerenciamentoProdutos.service;

import com.java.GerenciamentoProdutos.model.ProdutoModel;
import com.java.GerenciamentoProdutos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> listarProdutos (){
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> listarProdutosId(Long id){
        Optional<ProdutoModel> produtoModel = produtoRepository.findById(id);
        return produtoModel;
    }

    public ProdutoModel criarProduto(ProdutoModel produto){
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public Optional<ProdutoModel> atualizarProduto(Long id, ProdutoModel produto){
        Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            ProdutoModel produtoExistente = produtoOptional.get();
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setCategoria(produto.getCategoria());
            produtoExistente.setPreco(produto.getPreco());
            produtoExistente.setQuantidade(produto.getQuantidade());
            produtoExistente.setDescricao(produto.getDescricao());
            ProdutoModel produtoAtualizado = produtoRepository.save(produtoExistente);
            return Optional.of(produtoAtualizado);
        } else {
            return Optional.empty();
        }
    }
}
