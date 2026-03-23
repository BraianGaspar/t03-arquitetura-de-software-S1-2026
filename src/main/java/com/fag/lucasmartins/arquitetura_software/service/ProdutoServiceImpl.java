package com.fag.lucasmartins.arquitetura_software.service;

import com.fag.lucasmartins.arquitetura_software.dto.ProdutoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.dto.ProdutoResponseDTO;
import com.fag.lucasmartins.arquitetura_software.model.Produto;
import com.fag.lucasmartins.arquitetura_software.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do Service
 * Responsabilidade Única (SRP): Orquestrar as regras de negócio
 */
@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    @Override
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO request) {
        // 1. Cria o objeto de domínio
        Produto produto = new Produto(
            request.getNome(),
            request.getPreco(),
            request.getEstoque()
        );
        
        // 2. Aplica a regra de negócio de validação de produto premium
        //    (Essa regra está no modelo de domínio rico)
        produto.validarProdutoPremium();
        
        // 3. Persiste no banco de dados (camada de dados)
        Produto salvo = produtoRepository.salvar(produto);
        
        // 4. Retorna a resposta formatada (DTO)
        return new ProdutoResponseDTO(salvo);
    }
    
    @Override
    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.buscarTodos()
            .stream()
            .map(ProdutoResponseDTO::new)
            .collect(Collectors.toList());
    }
    
    @Override
    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return new ProdutoResponseDTO(produto);
    }
    
    @Override
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO request) {
        Produto produto = produtoRepository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        
        // Atualiza os dados usando o método do domínio rico
        produto.atualizarDados(
            request.getNome(),
            request.getPreco(),
            request.getEstoque()
        );
        
        // Revalida a regra de produto premium
        produto.validarProdutoPremium();
        
        // Persiste a atualização
        Produto atualizado = produtoRepository.atualizar(produto);
        
        return new ProdutoResponseDTO(atualizado);
    }
    
    @Override
    public void deletarProduto(Long id) {
        if (!produtoRepository.existePorId(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deletar(id);
    }
}