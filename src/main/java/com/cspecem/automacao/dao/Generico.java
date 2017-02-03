package com.cspecem.automacao.dao;

import java.io.Serializable;
import java.util.List;

public interface Generico<T, ID extends Serializable> {
	
	public T salvar(T object);
	public T atualizar(T object);
	public void deletar(ID id);
	public T porId(ID id);
	public List<T> todos();
	public List<T> listar(String value);

}
