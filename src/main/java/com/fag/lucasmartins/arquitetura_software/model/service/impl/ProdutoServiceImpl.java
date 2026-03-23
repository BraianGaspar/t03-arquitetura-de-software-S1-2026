package com.fag.lucasmartins.arquitetura_software.model.service.impl;

import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.model.repository.ProdutoRepository;
import com.fag.lucasmartins.arquitetura_software.model.service.ProdutoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    @Override
    public ProdutoBO criarProduto(ProdutoBO bo) {
        bo.validarProdutoPremium();
        return produtoRepository.salvar(bo);
    }
    
    @Override
    public List<ProdutoBO> listarProdutos() {
        return produtoRepository.buscarTodos();
    }
    
    @Override
    public ProdutoBO buscarProdutoPorId(Long id) {
        return produtoRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }
    
    @Override
    public ProdutoBO atualizarProduto(Long id, ProdutoBO bo) {
        ProdutoBO existente = buscarProdutoPorId(id);
        existente.atualizarDados(bo.getNome(), bo.getPreco(), bo.getEstoque());
        existente.validarProdutoPremium();
        return produtoRepository.atualizar(existente);
    }
    
    @Override
    public void deletarProduto(Long id) {
        if (!produtoRepository.existePorId(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deletar(id);
    }
}
