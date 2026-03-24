package com.fag.lucasmartins.arquitetura_software.view.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO de entrada e saída - Camada de View
 */
public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    private BigDecimal precoFinal;

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    private Integer estoque;

    private Boolean descontoAtacadoAplicado;

    public ProdutoDTO() {}

    public ProdutoDTO(Long id, String nome, BigDecimal preco, BigDecimal precoFinal, Integer estoque, Boolean descontoAtacadoAplicado) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.precoFinal = precoFinal;
        this.estoque = estoque;
        this.descontoAtacadoAplicado = descontoAtacadoAplicado;
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
    public Boolean getDescontoAtacadoAplicado() { return descontoAtacadoAplicado; }
    public void setDescontoAtacadoAplicado(Boolean descontoAtacadoAplicado) { this.descontoAtacadoAplicado = descontoAtacadoAplicado; }
}
