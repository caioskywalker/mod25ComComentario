package br.com.cfarias.services;


import br.com.cfarias.dao.IclientDAO;
import br.com.cfarias.domain.Cliente;
import br.com.cfarias.exceptions.TipoChaveNaoEncontradaException;

public class ClienteService implements IClienteService {

	private IclientDAO clientDAO;
	
	
	public ClienteService(IclientDAO clienteDao) {
		this.clientDAO = clienteDao;
		
	}

	
	public Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException {
		return clientDAO.cadastrar(cliente);
	}


	public Cliente buscarPorCpf(Long cpf) {
		return clientDAO.consultar(cpf);
	}


	@Override
	public void excluir(Long cpf) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException {
		clientDAO.alterar(cliente);
		
	}






}
