package com.fag.lucasmartins.arquitetura_software.model.repository.impl;

import com.fag.lucasmartins.arquitetura_software.model.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.model.repository.ProdutoRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public ProdutoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private final RowMapper<ProdutoBO> rowMapper = (rs, rowNum) -> new ProdutoBO(
        rs.getLong("id"),
        rs.getString("nome"),
        rs.getBigDecimal("preco"),
        rs.getBigDecimal("preco_final"),
        rs.getInt("estoque")
    );
    
    @Override
    public ProdutoBO salvar(ProdutoBO bo) {
        String sql = "INSERT INTO produto (nome, preco, preco_final, estoque) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            bo.getNome(), 
            bo.getPreco(), 
            bo.getPrecoFinal(),
            bo.getEstoque()
        );
        
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        bo.setId(id);
        return bo;
    }
    
    @Override
    public Optional<ProdutoBO> buscarPorId(Long id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try {
            ProdutoBO bo = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(bo);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    
    @Override
    public List<ProdutoBO> buscarTodos() {
        String sql = "SELECT * FROM produto";
        return jdbcTemplate.query(sql, rowMapper);
    }
    
    @Override
    public ProdutoBO atualizar(ProdutoBO bo) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, preco_final = ?, estoque = ? WHERE id = ?";
        jdbcTemplate.update(sql,
            bo.getNome(),
            bo.getPreco(),
            bo.getPrecoFinal(),
            bo.getEstoque(),
            bo.getId()
        );
        return bo;
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
