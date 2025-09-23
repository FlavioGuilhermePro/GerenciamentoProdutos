package com.java.GerenciamentoProdutos.service;

import com.java.GerenciamentoProdutos.dto.ProdutoDTO;
import com.java.GerenciamentoProdutos.model.CategoriaModel;
import com.java.GerenciamentoProdutos.model.ProdutoModel;
import com.java.GerenciamentoProdutos.repository.CategoriaRepository;
import com.java.GerenciamentoProdutos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<ProdutoDTO> listarProdutos (){
        return produtoRepository.findAll().stream()
                .map( produto -> {
                    ProdutoDTO dto = new ProdutoDTO();
                    dto.setNome(produto.getNome());
                    dto.setCategoriaId(produto.getCategoria().getId());
                    dto.setCategoriaNome(produto.getCategoria().getNome());
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
                    dto.setCategoriaId(produto.getCategoria().getId());
                    dto.setCategoriaNome(produto.getCategoria().getNome());
                    dto.setDescricao(produto.getDescricao());
                    dto.setPreco(produto.getPreco());
                    dto.setQuantidade(produto.getQuantidade());
                    return dto;
                });

    }

    public ProdutoModel criarProduto(ProdutoDTO produtoDTO){
        Optional<CategoriaModel> categoriaOptional = categoriaRepository.findById(produtoDTO.getCategoriaId());
        if(categoriaOptional.isEmpty()){
            return null;
        }
        CategoriaModel categoria = categoriaOptional.get();
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setCategoria(categoria);
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
