package com.cspecem.automacao.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.cspecem.automacao.model.Produto;
import com.cspecem.automacao.repository.Produtos;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;
	
	public Produto salvar(Produto produto) throws NegocioException {
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.salvar(produto);
	}
	
}
