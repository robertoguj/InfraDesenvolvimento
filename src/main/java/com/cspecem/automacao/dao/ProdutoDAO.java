package com.cspecem.automacao.dao;

import java.util.List;

import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.filter.ProdutoFilter;
import com.cspecem.automacao.service.NegocioException;

public interface ProdutoDAO extends Generico<Produto, Long> {
	
	//public Produto guardar(Produto produto);
	//public void remover(Produto produto) throws NegocioException;
	public Produto porSku(String sku);
	public List<Produto> filtrados(ProdutoFilter filtro);
	//public Produto porId(Long id);
	public List<Produto> porNome(String nome);

}
