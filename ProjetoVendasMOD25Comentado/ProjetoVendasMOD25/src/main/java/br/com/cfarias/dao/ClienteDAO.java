package br.com.cfarias.dao;

import br.com.cfarias.dao.generics.GenericDAO;
import br.com.cfarias.domain.Cliente;

public class ClienteDAO extends GenericDAO<Cliente,Long> implements IclientDAO {

	@Override
	public Class<Cliente> getTipoClasse() {
		return Cliente.class; 
		//Getter da classe cliente.
	}

	@Override
	public void atualiarDados(Cliente entity, Cliente entityCadastrado) {
		// A chave é a entidade da Classe Cliente, e o valor é o Cliente cadastrado, um dado serializavel.
		
	}

//	@Override
//	public Boolean salvar(Cliente cliente) {
//		return true;
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Cliente buscarPorCpf(Long cpf) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void excluir(Long cpf) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void alterar(Cliente cliente) {
//		// TODO Auto-generated method stub
//		
//	}


	

}
