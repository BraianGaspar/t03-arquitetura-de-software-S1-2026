package com.fag.lucasmartins.arquitetura_software.repository;

import com.fag.lucasmartins.arquitetura_software.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Implementação concreta do Repository
 * Responsabilidade Única (SRP): Apenas acesso a dados
 * Nenhuma regra de negócio aqui!
 */
@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public ProdutoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private final RowMapper<Produto> rowMapper = (rs, rowNum) -> new Produto(
        rs.getLong("id"),
        rs.getString("nome"),
        rs.getBigDecimal("preco"),
        rs.getBigDecimal("preco_final"),
        rs.getInt("estoque")
    );
    
    @Override
    public Produto salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, preco_final, estoque) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            produto.getNome(), 
            produto.getPreco(), 
            produto.getPrecoFinal(),
            produto.getEstoque()
        );
        
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        produto.setId(id);
        return produto;
    }
    
    @Override
    public Optional<Produto> buscarPorId(Long id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try {
            Produto produto = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(produto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    @Override
    public List<Produto> buscarTodos() {
        String sql = "SELECT * FROM produto";
        return jdbcTemplate.query(sql, rowMapper);
    }
    
    @Override
    public Produto atualizar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, preco_final = ?, estoque = ? WHERE id = ?";
        jdbcTemplate.update(sql,
            produto.getNome(),
            produto.getPreco(),
            produto.getPrecoFinal(),
            produto.getEstoque(),
            produto.getId()
        );
        return produto;
    }
    
    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    @Override
    public boolean existePorId(Long id) {
        String sql = "SELECT COUNT(*) FROM produto WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}