package com.fag.lucasmartins.arquitetura_software.controller;

import com.fag.lucasmartins.arquitetura_software.dto.ProdutoRequestDTO;
import com.fag.lucasmartins.arquitetura_software.dto.ProdutoResponseDTO;
import com.fag.lucasmartins.arquitetura_software.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller Refatorado
 * Responsabilidade Única (SRP): Apenas receber requisições HTTP e delegar para o Service
 * 
 * ANTES: Tinha regras de negócio, SQL e Map<String, Object>
 * AGORA: Apenas HTTP, DTOs tipados e delegação para o Service
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;
    
    // Injeção de dependência via construtor (DIP)
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    /**
     * Cadastra um novo produto
     * ANTES: Recebia Map<String, Object>, validava manualmente, aplicava regras de negócio e SQL
     * AGORA: Recebe DTO tipado, delega para o Service
     */
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(
            @Valid @RequestBody ProdutoRequestDTO request) {
        ProdutoResponseDTO response = produtoService.criarProduto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Lista todos os produtos
     */
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }
    
    /**
     * Busca um produto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProduto(@PathVariable Long id) {
        ProdutoResponseDTO produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }
    
    /**
     * Atualiza um produto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO request) {
        ProdutoResponseDTO produto = produtoService.atualizarProduto(id, request);
        return ResponseEntity.ok(produto);
    }
    
    /**
     * Remove um produto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}