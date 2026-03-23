package com.fag.lucasmartins.arquitetura_software.model.repository;

import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    ProdutoBO salvar(ProdutoBO bo);
    Optional<ProdutoBO> buscarPorId(Long id);
    List<ProdutoBO> buscarTodos();
    ProdutoBO atualizar(ProdutoBO bo);
    void deletar(Long id);
    boolean existePorId(Long id);
}
