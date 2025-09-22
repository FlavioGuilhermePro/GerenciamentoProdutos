package com.java.GerenciamentoProdutos.service;

import com.java.GerenciamentoProdutos.dto.ProdutoDTO;
import com.java.GerenciamentoProdutos.model.ProdutoModel;
import com.java.GerenciamentoProdutos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> listarProdutos (){
        return produtoRepository.findAll().stream()
                .map( produto -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setNome(produto.getNome());
                    dto.setCategoria(produto.getCategoria());
                    dto.setDescricao(produto.getDescricao());
                    dto.setPreco(produto.getPreco());
                    dto.setQuantidade(produto.getQuantidade());
                    return dto;

                }).collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> listarProdutosId(Long id){
        return produtoRepository.findById(id)
                .map(produto -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setNome(produto.getNome());
                    dto.setCategoria(produto.getCategoria());
                    dto.setDescricao(produto.getDescricao());
                    dto.setPreco(produto.getPreco());
                    dto.setQuantidade(produto.getQuantidade());
                    return dto;
                });

    }

    public ProdutoModel criarProduto(ProdutoDTO produtoDTO){
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(produtoDTO.getNome());
        produto.setCategoria(produtoDTO.getCategoria());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public Optional<ProdutoModel> atualizarProduto(Long id, ProdutoDTO produto){
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
