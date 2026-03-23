package com.fag.lucasmartins.arquitetura_software.dto;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import java.math.BigDecimal;

/**
 * DTO de saída - Formata a resposta da API
 */
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private BigDecimal precoFinal;
    private Integer estoque;
    private Boolean descontoAtacadoAplicado;
    
    public ProdutoResponseDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.precoFinal = produto.getPrecoFinal();
        this.estoque = produto.getEstoque();
        // Verifica se o desconto foi aplicado (precoFinal < preco)
        this.descontoAtacadoAplicado = produto.getPrecoFinal().compareTo(produto.getPreco()) < 0;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public BigDecimal getPrecoFinal() { return precoFinal; }
    public Integer getEstoque() { return estoque; }
    public Boolean getDescontoAtacadoAplicado() { return descontoAtacadoAplicado; }
}