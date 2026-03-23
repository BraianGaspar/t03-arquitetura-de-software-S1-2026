package com.fag.lucasmartins.arquitetura_software.model.bo;

import java.math.BigDecimal;

/**
 * Modelo de Domínio Rico (Business Object)
 * Contém todas as regras de negócio do produto
 */
public class ProdutoBO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private BigDecimal precoFinal;
    private Integer estoque;

    public ProdutoBO(Long id, String nome, BigDecimal preco, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.precoFinal = calcularPrecoFinal();
    }

    public ProdutoBO(Long id, String nome, BigDecimal preco, BigDecimal precoFinal, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.precoFinal = precoFinal;
        this.estoque = estoque;
    }

    /**
     * REGRA DE NEGÓCIO 1: Validação de Produto Premium
     */
    public void validarProdutoPremium() {
        if (nome != null && nome.toLowerCase().contains("premium")) {
            if (preco.compareTo(new BigDecimal("100.00")) < 0) {
                throw new IllegalArgumentException(
                    "Produtos Premium não podem custar menos de R$ 100,00."
                );
            }
        }
    }

    /**
     * REGRA DE NEGÓCIO 2: Cálculo do Preço Final com Desconto de Atacado
     */
    public BigDecimal calcularPrecoFinal() {
        if (estoque != null && estoque >= 50) {
            return preco.multiply(new BigDecimal("0.90"));
        }
        return preco;
    }

    public void atualizarDados(String nome, BigDecimal preco, Integer estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.precoFinal = calcularPrecoFinal();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public BigDecimal getPrecoFinal() { return precoFinal; }
    public void setPrecoFinal(BigDecimal precoFinal) { this.precoFinal = precoFinal; }
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
}
