package br.com.cfarias.services;

import br.com.cfarias.domain.Cliente;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;

public interface IClienteService {

	Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException;

	Cliente buscarPorCpf(Long cpf);
	
	void excluir(Long cpf);

	void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;
}
