package com.cspecem.automacao.dao;

import java.util.List;

import com.cspecem.automacao.model.Produto;

public interface ProdutoDAO extends Generico<Produto, Long> {
	
	//public Produto guardar(Produto produto);
	//public void remover(Produto produto) throws NegocioException;
	//public Produto porId(Long id);
	
	public Produto porSku(String sku);
	public List<Produto> porNome(String nome);

}
