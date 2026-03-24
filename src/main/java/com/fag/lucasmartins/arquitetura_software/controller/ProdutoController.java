package com.fag.lucasmartins.arquitetura_software.controller;

import com.fag.lucasmartins.arquitetura_software.controller.mapper.ProdutoDTOMapper;
import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.model.service.ProdutoService;
import com.fag.lucasmartins.arquitetura_software.view.dto.ProdutoDTO;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;
    
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@Valid @RequestBody ProdutoDTO request) {
        ProdutoBO bo = ProdutoDTOMapper.toBo(request);
        ProdutoBO salvo = produtoService.criarProduto(bo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoDTOMapper.toDto(salvo));
    }
    
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        List<ProdutoDTO> produtos = produtoService.listarProdutos()
            .stream()
            .map(ProdutoDTOMapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable Long id) {
        ProdutoBO bo = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(ProdutoDTOMapper.toDto(bo));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO request) {
        ProdutoBO bo = ProdutoDTOMapper.toBo(request);
        bo.setId(id);
        ProdutoBO atualizado = produtoService.atualizarProduto(id, bo);
        return ResponseEntity.ok(ProdutoDTOMapper.toDto(atualizado));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
