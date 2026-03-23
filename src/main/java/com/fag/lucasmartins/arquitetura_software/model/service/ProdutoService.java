package com.fag.lucasmartins.arquitetura_software.model.service;

import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import java.util.List;

public interface ProdutoService {
    ProdutoBO criarProduto(ProdutoBO bo);
    List<ProdutoBO> listarProdutos();
    ProdutoBO buscarProdutoPorId(Long id);
    ProdutoBO atualizarProduto(Long id, ProdutoBO bo);
    void deletarProduto(Long id);
}
