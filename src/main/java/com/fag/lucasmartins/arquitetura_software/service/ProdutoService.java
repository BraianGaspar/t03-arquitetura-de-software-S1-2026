package com.fag.lucasmartins.arquitetura_software.service;

import com.fag.lucasmartins.arquitetura_software.dto.ProdutoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.dto.ProdutoResponseDTO;
import java.util.List;

/**
 * Interface do Service - Inversão de Dependência
 */
public interface ProdutoService {
    ProdutoResponseDTO criarProduto(ProdutoRequestDTO request);
    List<ProdutoResponseDTO> listarProdutos();
    ProdutoResponseDTO buscarProdutoPorId(Long id);
    ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO request);
    void deletarProduto(Long id);
}