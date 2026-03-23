package com.fag.lucasmartins.arquitetura_software.repository;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import java.util.List;
import java.util.Optional;

/**
 * Interface do Repository - Princípio da Inversão de Dependência (DIP)
 */
public interface ProdutoRepository {
    Produto salvar(Produto produto);
    Optional<Produto> buscarPorId(Long id);
    List<Produto> buscarTodos();
    Produto atualizar(Produto produto);
    void deletar(Long id);
    boolean existePorId(Long id);
}