package com.fag.lucasmartins.arquitetura_software.controller.mapper;

import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.view.dto.ProdutoDTO;

public class ProdutoDTOMapper {

    private ProdutoDTOMapper() {
    }

    public static ProdutoBO toBo(ProdutoDTO dto) {
        return new ProdutoBO(dto.getId(), dto.getNome(), dto.getPreco(), dto.getEstoque());
    }

    public static ProdutoDTO toDto(ProdutoBO bo) {
        Boolean descontoAtacadoAplicado = bo.getPrecoFinal().compareTo(bo.getPreco()) < 0;
        return new ProdutoDTO(bo.getId(), bo.getNome(), bo.getPreco(), bo.getPrecoFinal(), bo.getEstoque(), descontoAtacadoAplicado);
    }
}
